package ru.owepkov.currencyapp.data.repository.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import ru.owepkov.currencyapp.BuildConfig
import javax.inject.Inject

class RequestParamsInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalUrl = original.url()

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter(API_KEY_NAME, BuildConfig.CURRENCY_API_KEY)
            .build()

        val newRequestBuilder = original.newBuilder()
            .url(newUrl)

        return chain.proceed(newRequestBuilder.build())
    }

    companion object {
        private const val API_KEY_NAME = "access_key"
    }
}