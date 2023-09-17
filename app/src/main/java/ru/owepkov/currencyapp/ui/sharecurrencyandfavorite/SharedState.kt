package ru.owepkov.currencyapp.ui.sharecurrencyandfavorite

data class SharedState(
    val needUpdate: Unit = Unit,
    val updateCurrencyRecordId: Long = -1
)