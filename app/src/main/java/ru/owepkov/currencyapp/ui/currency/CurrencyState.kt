package ru.owepkov.currencyapp.ui.currency

import ru.owepkov.currencyapp.data.models.ui.CurrencyListItem

data class CurrencyState(
    val isLoading: Boolean = false,
    val currencyList: List<CurrencyListItem> = emptyList(),
    val error: Throwable? = null
)