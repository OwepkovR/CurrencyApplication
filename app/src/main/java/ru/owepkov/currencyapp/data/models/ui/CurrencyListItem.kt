package ru.owepkov.currencyapp.data.models.ui

data class CurrencyListItem(
    val currency: Float,
    val title: String,
    val isFavorite: Boolean,
    val localId: Long? = null
)