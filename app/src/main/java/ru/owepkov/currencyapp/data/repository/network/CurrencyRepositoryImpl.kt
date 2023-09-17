package ru.owepkov.currencyapp.data.repository.network

import ru.owepkov.currencyapp.data.models.local.FavoriteCurrencyEntity
import ru.owepkov.currencyapp.data.models.ui.CurrencyListItem
import ru.owepkov.currencyapp.data.models.ui.FavoriteListItem
import ru.owepkov.currencyapp.data.repository.CurrencyRepository
import ru.owepkov.currencyapp.data.source.CurrencyLocalDataSource
import ru.owepkov.currencyapp.data.source.CurrencyNetworkDataSource
import java.lang.Exception
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val localDataSource: CurrencyLocalDataSource,
    private val networkDataSource: CurrencyNetworkDataSource
) : CurrencyRepository {
    override suspend fun getCurrencyByBase(base: String): Result<List<CurrencyListItem>> {
        val networkResult = networkDataSource.getCurrencyByBase(base)

        if (networkResult.isFailure) {
            return Result.failure(
                networkResult.exceptionOrNull() ?: Exception("Безрезультатный вызов")
            )
        } else {
            val localResult = localDataSource.findFavoriteItems(base)
            val networkData = networkResult.getOrNull()
            val resultList = mutableListOf<CurrencyListItem>()

            networkData?.rates?.forEach { networkItem ->
                var itemIsFavorite = false
                val localItem =
                    localResult.find { it.baseCurrency == base && it.secondaryCurrency == networkItem.key }

                if (localItem != null) {
                    itemIsFavorite = true
                }

                resultList.add(
                    CurrencyListItem(
                        currency = networkItem.value,
                        title = networkItem.key,
                        isFavorite = itemIsFavorite,
                        localId = localItem?.localId
                    )
                )
            }

            return Result.success(resultList)
        }
    }

    override suspend fun setFavoriteCurrency(
        localId: Long?,
        isFavorite: Boolean,
        secondaryCurrency: String,
        baseCurrency: String,
        amount: Float
    ): Result<FavoriteCurrencyEntity?> {
        return try {
            if (localId != null && !isFavorite) {
                localDataSource.removeFavorite(localId)
                Result.success(null)
            } else {
                Result.success(localDataSource.insertFavorite(baseCurrency, secondaryCurrency, amount))
            }
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
    }

    override suspend fun getFavoriteItemsList(): Result<List<FavoriteCurrencyEntity>> {
        return try {
            Result.success(localDataSource.getFavoriteList())
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
    }

    override suspend fun deleteFavoriteItem(localId: Long): Result<Boolean> {
        return try {
            localDataSource.removeFavorite(localId)
            Result.success(true)
        } catch (exception: Throwable) {
            Result.failure(exception)
        }
    }
}