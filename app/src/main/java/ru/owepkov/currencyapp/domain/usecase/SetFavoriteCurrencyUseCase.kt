package ru.owepkov.currencyapp.domain.usecase

import ru.owepkov.currencyapp.data.models.ui.CurrencyListItem
import ru.owepkov.currencyapp.data.repository.CurrencyRepository
import javax.inject.Inject

class SetFavoriteCurrencyUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    suspend operator fun invoke(
        item: CurrencyListItem,
        baseCurrency: String
    ): Result<CurrencyListItem> {
        val result =
            repository.setFavoriteCurrency(
                item.localId,
                item.isFavorite,
                item.title,
                baseCurrency,
                item.currency
            )

        return if (result.isSuccess) {
            val resultItem = result.getOrNull()

            Result.success(
                CurrencyListItem(
                    currency = item.currency,
                    title = item.title,
                    isFavorite = item.isFavorite,
                    localId = resultItem?.localId
                )
            )
        } else {
            Result.failure(result.exceptionOrNull()!!)
        }
    }
}