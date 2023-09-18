package ru.owepkov.currencyapp.data.repository

import ru.owepkov.currencyapp.data.models.local.FavoriteCurrencyEntity
import ru.owepkov.currencyapp.data.models.ui.CurrencyListItem

interface CurrencyRepository {
    suspend fun getCurrencyByBase(base: String): Result<List<CurrencyListItem>>

    suspend fun setFavoriteCurrency(
        localId: Long?,
        isFavorite: Boolean,
        secondaryCurrency: String,
        baseCurrency: String,
        amount: Float
    ): Result<FavoriteCurrencyEntity?>

    suspend fun getFavoriteItemsList(): Result<List<FavoriteCurrencyEntity>>

    suspend fun deleteFavoriteItem(localId: Long): Result<Boolean>
}