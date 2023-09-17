package ru.owepkov.currencyapp.data.models.network

class ApiException(val code: Int, override val message: String? = null) : Throwable(message)