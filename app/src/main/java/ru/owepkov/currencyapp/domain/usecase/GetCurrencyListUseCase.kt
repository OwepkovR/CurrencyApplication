package ru.owepkov.currencyapp.domain.usecase

import ru.owepkov.currencyapp.data.models.ui.CurrencyListItem
import ru.owepkov.currencyapp.data.repository.CurrencyRepository
import javax.inject.Inject

class GetCurrencyListUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    suspend operator fun invoke(baseCurrency: String): Result<List<CurrencyListItem>?> {
        val response = repository.getCurrencyByBase(baseCurrency)

        return if (response.isFailure) {
            Result.failure(response.exceptionOrNull()!!)
        } else {
            Result.success(response.getOrNull())
        }
    }
}