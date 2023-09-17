package ru.owepkov.currencyapp.ui.sharecurrencyandfavorite

import ru.owepkov.currencyapp.ui.base.BaseViewModel
import javax.inject.Inject

class SharedViewModel @Inject constructor() : BaseViewModel<SharedState>() {
    override var state: SharedState = SharedState()

    fun onCurrencyStateChange() {
        state = SharedState(needUpdate = Unit).apply { setStateValue() }
    }

    fun onRemoveFavorite(localId: Long) {
        state = SharedState(updateCurrencyRecordId = localId).apply { postStateValue() }
    }
}