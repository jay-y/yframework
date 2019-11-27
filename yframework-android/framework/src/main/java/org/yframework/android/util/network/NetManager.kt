package org.yframework.android.util.network

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object NetManager {

    private const val CONNECT_TIMEOUT_MILLS = 10 * 1000L
    private const val READ_TIMEOUT_MILLS = 10 * 1000L

    var commonProvider: NetProvider? = null
        private set

    private val providerMap = HashMap<String, NetProvider>()
    private val retrofitMap = HashMap<String, Retrofit>()
    private val clientMap = HashMap<String, OkHttpClient>()

    operator fun <S> get(baseURL: String, service: Class<S>, provider: NetProvider? = null): S {
        return getRetrofit(baseURL, provider).create(service)
    }

    @JvmOverloads
    fun getRetrofit(baseURL: String, provider: NetProvider? = null): Retrofit {
        var p = provider
        check(!empty(baseURL)) { "基础URL不能为空" }
        if (retrofitMap[baseURL] != null) {
            return retrofitMap[baseURL]!!
        }

        if (p == null) {
            p = providerMap[baseURL]
            if (p == null) {
                p = commonProvider
            }
        }
        checkProvider(p)

        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()

        val builder = Retrofit.Builder()
            .baseUrl(baseURL)
            .client(getClient(baseURL, p!!))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))

        val retrofit = builder.build()
        retrofitMap[baseURL] = retrofit
        providerMap[baseURL] = p

        return retrofit
    }

    fun getRetrofitMap(): Map<String, Retrofit> {
        return retrofitMap
    }

    fun getClientMap(): Map<String, OkHttpClient> {
        return clientMap
    }

    fun registerProvider(provider: NetProvider) {
        commonProvider = provider
    }

    fun registerProvider(baseURL: String, provider: NetProvider) {
        providerMap[baseURL] = provider
    }

    fun clearCache() {
        retrofitMap.clear()
        clientMap.clear()
    }

    private fun empty(baseURL: String?): Boolean {
        return baseURL == null || baseURL.isEmpty()
    }

    private fun getClient(baseURL: String, provider: NetProvider): OkHttpClient {
        if (empty(baseURL)) {
            throw IllegalStateException("baseURL can not be null")
        }
        if (clientMap[baseURL] != null) {
            return clientMap[baseURL]!!
        }

        checkProvider(provider)

        val builder = OkHttpClient.Builder()

        builder.connectTimeout(
            if (provider.configConnectTimeoutSecs() != 0L)
                provider.configConnectTimeoutSecs()
            else
                CONNECT_TIMEOUT_MILLS, TimeUnit.SECONDS
        )
        builder.readTimeout(
            if (provider.configReadTimeoutSecs() != 0L)
                provider.configReadTimeoutSecs()
            else
                READ_TIMEOUT_MILLS, TimeUnit.SECONDS
        )

        builder.writeTimeout(
            if (provider.configWriteTimeoutSecs() != 0L)
                provider.configReadTimeoutSecs()
            else
                READ_TIMEOUT_MILLS, TimeUnit.SECONDS
        )
        val cookieJar = provider.configCookie()
        if (cookieJar != null) {
            builder.cookieJar(cookieJar)
        }
        provider.configHttps(builder)

        val handler = provider.configHandler()
        builder.addInterceptor(NetInterceptor(handler))

        val interceptors = provider.configInterceptors()
        if (!empty(interceptors)) {
            for (interceptor in interceptors!!) {
                builder.addInterceptor(interceptor)
            }
        }

        if (provider.configLogEnable()) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }

        val client = builder.build()
        clientMap[baseURL] = client
        providerMap[baseURL] = provider

        return client
    }

    private fun empty(interceptors: Array<Interceptor>?): Boolean {
        return interceptors == null || interceptors.isEmpty()
    }

    private fun checkProvider(provider: NetProvider?) {
        if (provider == null) {
            throw IllegalStateException("must register provider first")
        }
    }
}
