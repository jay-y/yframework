package org.yframework.android.util.network

import android.content.Context
import android.os.RemoteException
import com.becypress.framework.android.BuildConfig
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.*
import java.io.IOException
import java.util.*

class BasicNetProvider(private val context: Context) :
    NetProvider {

    private val traceId: String
        get() = UUID.randomUUID().toString()

    override fun configInterceptors(): Array<Interceptor>? {
        return null
    }

    override fun configHttps(builder: OkHttpClient.Builder) {
    }

    override fun configCookie(): CookieJar? =
        PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))

    override fun configHandler(): RequestHandler {

        return HeaderHandler()
    }

    override fun configConnectTimeoutSecs(): Long {
        return CONNECT_TIME_OUT
    }

    override fun configReadTimeoutSecs(): Long {
        return READ_TIME_OUT
    }

    override fun configWriteTimeoutSecs(): Long {
        return WRITE_TIME_OUT
    }

    override fun configLogEnable(): Boolean {
        return BuildConfig.DEBUG
    }

    inner class HeaderHandler : RequestHandler {

        override fun onBeforeRequest(request: Request, chain: Interceptor.Chain): Request {
            return request
        }

        @Throws(IOException::class)
        override fun onAfterRequest(response: Response, chain: Interceptor.Chain): Response {
            var ex: RemoteException? = null
            when {
                401 == response.code() -> throw RemoteException("登录已过期,请重新登录")
                403 == response.code() -> ex = RemoteException("禁止访问")
                404 == response.code() -> ex = RemoteException("链接错误")
                503 == response.code() -> ex = RemoteException("服务器升级中...")
                response.code() > 300 -> {
                    val message = response.body()!!.string()
                    ex = if (message.isNullOrEmpty()) {
                        RemoteException("服务器内部错误")
                    } else {
                        RemoteException(message)
                    }
                }
            }
            if (ex != null) {
                throw ex
            }
            return response
        }
    }

    companion object {
        const val CONNECT_TIME_OUT: Long = 20

        const val READ_TIME_OUT: Long = 180

        const val WRITE_TIME_OUT: Long = 30
    }
}
