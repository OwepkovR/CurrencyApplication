package ru.owepkov.currencyapp.data.models.network

import java.io.Serializable

data class CurrencyResponse(
    val success: Boolean,
    val rates: Map<String, Float>,
    val error: CurrencyResponseError
) : Serializable

data class CurrencyResponseError(
    val code: Int,
    val type: String
) : Serializable