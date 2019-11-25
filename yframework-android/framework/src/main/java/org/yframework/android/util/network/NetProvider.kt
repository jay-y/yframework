package org.yframework.android.util.network

import okhttp3.CookieJar
import okhttp3.Interceptor
import okhttp3.OkHttpClient

interface NetProvider {

    fun configInterceptors(): Array<Interceptor>?

    fun configHttps(builder: OkHttpClient.Builder)

    fun configCookie(): CookieJar?

    fun configHandler(): RequestHandler

    fun configConnectTimeoutSecs(): Long

    fun configReadTimeoutSecs(): Long

    fun configWriteTimeoutSecs(): Long

    fun configLogEnable(): Boolean

}
