package ru.owepkov.currencyapp.ui.sort

import ru.owepkov.currencyapp.data.models.ui.SortDirection
import ru.owepkov.currencyapp.domain.SortPreferenceManager
import ru.owepkov.currencyapp.ui.base.BaseViewModel
import javax.inject.Inject

class SortViewModel @Inject constructor(
    private val sortPreferenceManager: SortPreferenceManager
) : BaseViewModel<SortState>() {
    override var state: SortState = SortState()

    fun loadSortParams() {
        val currencySortDirection = sortPreferenceManager.getCurrencySort()
        val alphabetSortDirection = sortPreferenceManager.getAlphabetSort()

        state = state
            .copy(alphabetSort = alphabetSortDirection, currencySort = currencySortDirection)
            .apply {
                postStateValue()
            }
    }

    fun setAlphabetSortDirection(sortDirection: SortDirection?) {
        setSortDirection(sortDirection, SortPreferenceManager.ALPHABET_SORT)
    }

    fun setCurrencySortDirection(sortDirection: SortDirection?) {
        setSortDirection(sortDirection, SortPreferenceManager.CURRENCY_SORT)
    }

    private fun setSortDirection(direction: SortDirection?, type: String) {
        sortPreferenceManager.setPreferences(type, direction?.stringCode ?: "")
    }
}