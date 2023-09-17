package ru.owepkov.currencyapp.data.source.network

import ru.owepkov.currencyapp.data.models.network.ApiException
import ru.owepkov.currencyapp.data.models.network.CurrencyResponse
import ru.owepkov.currencyapp.data.repository.network.CurrencyApi
import ru.owepkov.currencyapp.data.source.CurrencyNetworkDataSource
import javax.inject.Inject

class CurrencyNetworkDataSourceImpl @Inject constructor(
    private val api: CurrencyApi
) : CurrencyNetworkDataSource {
    override suspend fun getCurrencyByBase(currencyBase: String): Result<CurrencyResponse> {
        return try {
            val response = api.getLatestForSelectedCurrency(currencyBase)

            if (response.success) {
                Result.success(response)
            } else {
                Result.failure(ApiException(response.error.code, response.error.type))
            }
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
    }
}