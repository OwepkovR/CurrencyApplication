package ru.owepkov.currencyapp.data.source

import ru.owepkov.currencyapp.data.models.network.CurrencyResponse

interface CurrencyNetworkDataSource {
    suspend fun getCurrencyByBase(currencyBase: String) : Result<CurrencyResponse>
}