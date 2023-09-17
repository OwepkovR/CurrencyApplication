package ru.owepkov.currencyapp.data.source

import ru.owepkov.currencyapp.data.models.local.FavoriteCurrencyEntity

interface CurrencyLocalDataSource {
    suspend fun findFavoriteItems(baseCurrency: String): List<FavoriteCurrencyEntity>

    suspend fun removeFavorite(localId: Long?)

    suspend fun insertFavorite(
        baseCurrency: String,
        secondaryCurrency: String,
        amount: Float
    ): FavoriteCurrencyEntity

    suspend fun getFavoriteList(): List<FavoriteCurrencyEntity>
}