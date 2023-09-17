package ru.owepkov.currencyapp.data.models.network

enum class ApiExceptionType(val code: Int) {
    SUBSCRIPTION_PLAN_ERROR(105),
    UNDEFINED(-1);

    companion object {
        fun from(inCode: Int) = values().find { it.code == inCode }
    }
}