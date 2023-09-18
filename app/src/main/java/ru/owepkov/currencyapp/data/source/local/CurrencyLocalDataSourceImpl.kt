package ru.owepkov.currencyapp.data.source.local

import ru.owepkov.currencyapp.data.models.local.FavoriteCurrencyEntity
import ru.owepkov.currencyapp.data.repository.local.LocalDatabase
import ru.owepkov.currencyapp.data.source.CurrencyLocalDataSource
import javax.inject.Inject

class CurrencyLocalDataSourceImpl @Inject constructor(
    private val database: LocalDatabase
) : CurrencyLocalDataSource {
    override suspend fun findFavoriteItems(baseCurrency: String): List<FavoriteCurrencyEntity> {
        return database.getCurrencyFavoriteDao().getFavoritesByCurrency(baseCurrency)
    }

    override suspend fun removeFavorite(localId: Long?) {
        database.getCurrencyFavoriteDao().removeFavorite(localId)
    }

    override suspend fun insertFavorite(
        baseCurrency: String,
        secondaryCurrency: String,
        amount: Float
    ): FavoriteCurrencyEntity {
        val entity = FavoriteCurrencyEntity(
            baseCurrency = baseCurrency,
            secondaryCurrency = secondaryCurrency,
            amount = amount
        )
        val localId = database.getCurrencyFavoriteDao().insert(entity)

        return entity.copy(localId = localId)
    }

    override suspend fun getFavoriteList(): List<FavoriteCurrencyEntity> {
        return database.getCurrencyFavoriteDao().selectAll()
    }
}