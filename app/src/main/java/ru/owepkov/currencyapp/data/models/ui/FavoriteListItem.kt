package ru.owepkov.currencyapp.data.models.ui

data class FavoriteListItem(
    val baseCurrency: String,
    val secondaryCurrency: String,
    val amount: Float,
    val localId: Long
)