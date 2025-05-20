package com.satc.integrador_ai.data.api

import com.satc.integrador_ai.data.local.UserSharedPreferencesHelper
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val userSharedPreferencesHelper: UserSharedPreferencesHelper
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${userSharedPreferencesHelper.getToken()}")
            .build()
        return chain.proceed(request)
    }
}