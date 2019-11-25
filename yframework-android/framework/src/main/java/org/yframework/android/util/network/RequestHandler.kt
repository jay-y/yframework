package org.yframework.android.util.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


interface RequestHandler {

    fun onBeforeRequest(request: Request, chain: Interceptor.Chain): Request

    @Throws(IOException::class)
    fun onAfterRequest(response: Response, chain: Interceptor.Chain): Response

}
