package com.example.movee.util.api

import android.content.Context
import com.example.movee.data.local.SharedPrefHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

const val HEADER_REQUIRE_SESSION_TOKEN =
    "${RequiresSessionTokenInterceptor.HEADER_KEY}: ${RequiresSessionTokenInterceptor.HEADER_VALUE}"

class RequiresSessionTokenInterceptor @Inject constructor(
    @ApplicationContext context: Context,
    private val sharedPrefHelper: SharedPrefHelper
) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()


        if (request.header(HEADER_KEY)?.toBoolean() == true) {
            val url = request.url.newBuilder()
                .addQueryParameter(
                    QUERY_PARAMETER_SESSION,
                    sharedPrefHelper.getSessionId()
                        ?: throw Exception("Unauthorized")
                )
                .build()

            request = request.newBuilder()
                .url(url)
                .build()
        }

        return chain.proceed(request)
    }

    companion object {
        private const val QUERY_PARAMETER_SESSION = "session_id"
        const val HEADER_KEY = "RequireSessionToken"
        const val HEADER_VALUE = "true"
    }
}