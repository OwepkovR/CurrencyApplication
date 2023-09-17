package ru.owepkov.currencyapp.domain.usecase

import ru.owepkov.currencyapp.data.repository.CurrencyRepository
import javax.inject.Inject

class RemoveFavoriteItemUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    suspend operator fun invoke(localId: Long): Result<Boolean> {
        return repository.deleteFavoriteItem(localId)
    }
}