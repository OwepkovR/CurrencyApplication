package ru.owepkov.currencyapp.ui.sort

import ru.owepkov.currencyapp.data.models.ui.SortDirection

data class SortState(
    val alphabetSort: SortDirection? = null,
    val currencySort: SortDirection? = null
)