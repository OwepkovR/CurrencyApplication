package ru.owepkov.currencyapp.domain.usecase

import ru.owepkov.currencyapp.data.models.ui.FavoriteListItem
import ru.owepkov.currencyapp.data.repository.CurrencyRepository
import javax.inject.Inject

class GetFavoriteListUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    suspend operator fun invoke(): Result<List<FavoriteListItem>> {
        val response = repository.getFavoriteItemsList()
        val resultList = mutableListOf<FavoriteListItem>()

        return if (response.isSuccess) {
            response.getOrNull()?.forEach {
                resultList.add(
                    FavoriteListItem(
                        it.baseCurrency,
                        it.secondaryCurrency,
                        it.amount,
                        it.localId
                    )
                )
            }
            Result.success(resultList)
        } else {
            Result.failure(response.exceptionOrNull()!!)
        }
    }
}