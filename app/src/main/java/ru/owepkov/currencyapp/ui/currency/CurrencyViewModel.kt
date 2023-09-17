package ru.owepkov.currencyapp.ui.currency

import android.text.TextUtils
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.owepkov.currencyapp.CoroutineContextProvider
import ru.owepkov.currencyapp.data.models.ui.CurrencyListItem
import ru.owepkov.currencyapp.domain.usecase.GetCurrencyListUseCase
import ru.owepkov.currencyapp.domain.usecase.SetFavoriteCurrencyUseCase
import ru.owepkov.currencyapp.ui.base.BaseViewModel
import java.util.concurrent.CopyOnWriteArrayList
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(
    private val coroutineContextProvider: CoroutineContextProvider,
    private val getCurrencyListUseCase: GetCurrencyListUseCase,
    private val setFavoriteCurrencyUseCase: SetFavoriteCurrencyUseCase
) : BaseViewModel<CurrencyState>() {
    override var state: CurrencyState = CurrencyState()

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        showError(throwable)
    }

    private val coroutineContext = coroutineContextProvider.IO + exceptionHandler

    fun getCurrencyList(selectedCurrency: String?) {

        if (TextUtils.isEmpty(selectedCurrency)) {
            state = state.copy(currencyList = emptyList()).apply { setStateValue() }
            return
        }

        state = CurrencyState(isLoading = true).apply { setStateValue() }

        viewModelScope.launch(coroutineContext) {
            val result = getCurrencyListUseCase(selectedCurrency!!)

            if (result.isSuccess) {
                state = state.copy(
                    isLoading = false,
                    currencyList = result.getOrNull() ?: emptyList(),
                    error = null
                ).apply { postStateValue() }
            } else {
                throw result.exceptionOrNull()!!
            }
        }
    }

    fun onFavoriteClick(currencyListItem: CurrencyListItem, selectedItem: String) {
        val newCurrencyList = mutableListOf<CurrencyListItem>().apply {
            addAll(state.currencyList)
        }
        val newItem = currencyListItem.copy(isFavorite = !currencyListItem.isFavorite)

        state = state.copy(isLoading = true).apply { setStateValue() }

        viewModelScope.launch(coroutineContext) {
            val result = setFavoriteCurrencyUseCase.invoke(
                currencyListItem.copy(isFavorite = !currencyListItem.isFavorite),
                selectedItem
            )

            if (result.isSuccess) {
                val resultItem = result.getOrNull()
                newCurrencyList.find { it == currencyListItem }?.let {
                    val index = newCurrencyList.indexOf(it)

                    newCurrencyList[index] = resultItem ?: newItem
                }

                state = state.copy(isLoading = false, currencyList = newCurrencyList, error = null).apply {
                    postStateValue()
                }
            } else {
                throw result.exceptionOrNull()!!
            }
        }
    }

    fun unselectFavorite(idLocal: Long) {
        viewModelScope.launch(coroutineContext) {
            val newItemsList = CopyOnWriteArrayList<CurrencyListItem>().apply { addAll(state.currencyList) }

            newItemsList.forEach {
                if (it.localId == idLocal) {
                    val index = newItemsList.indexOf(it)

                    newItemsList.removeAt(index)
                    newItemsList.add(index,it.copy(isFavorite = false, localId = null))
                }
            }

            state = state.copy(currencyList = newItemsList).apply { postStateValue() }
        }
    }

    private fun showError(throwable: Throwable) {
        state = state.copy(isLoading = false, error = throwable, currencyList = emptyList()).apply {
            postStateValue()
        }
    }
}