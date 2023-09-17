package ru.owepkov.currencyapp.data.repository.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.owepkov.currencyapp.data.models.network.CurrencyResponse

interface CurrencyApi {
    @GET("/latest")
    suspend fun getLatestForSelectedCurrency(@Query("base") base: String) : CurrencyResponse
}