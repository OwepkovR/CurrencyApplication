package ru.owepkov.currencyapp.ui.favorite

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.owepkov.currencyapp.ui.base.BaseViewModel
import ru.owepkov.currencyapp.CoroutineContextProvider
import ru.owepkov.currencyapp.data.models.ui.FavoriteListItem
import ru.owepkov.currencyapp.domain.usecase.GetFavoriteListUseCase
import ru.owepkov.currencyapp.domain.usecase.RemoveFavoriteItemUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    private val getFavoriteListUseCase: GetFavoriteListUseCase,
    private val removeFavoriteItemUseCase: RemoveFavoriteItemUseCase
) : BaseViewModel<FavoriteState>() {
    override var state = FavoriteState()

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        showError(throwable)
    }

    private val coroutineContext = coroutineContextProvider.IO + exceptionHandler

    fun removeFavorite(localId: Long) {
        val newItemsList = mutableListOf<FavoriteListItem>().apply {
            addAll(state.favoriteItems)
        }

        viewModelScope.launch(coroutineContext) {
            val result = removeFavoriteItemUseCase(localId)

            if (result.isSuccess) {
                newItemsList.removeIf { it.localId == localId }

                state = state.copy(favoriteItems = newItemsList).apply { postStateValue() }
            } else {
                val exception = result.exceptionOrNull()

                if (exception != null) {
                    throw exception
                } else {
                    throw UnknownError()
                }
            }
        }
    }

    fun getFavoriteList() {
        state = FavoriteState(isLoading = true).apply { setStateValue() }

        viewModelScope.launch(coroutineContext) {
            val response = getFavoriteListUseCase()

            if (response.isSuccess) {
                state = state.copy(
                    isLoading = false,
                    favoriteItems = response.getOrNull() ?: emptyList(),
                    error = null
                ).apply { postStateValue() }
            } else {
                val exception = response.exceptionOrNull()

                if (exception != null) {
                    throw exception
                } else {
                    throw UnknownError()
                }
            }
        }
    }

    private fun showError(throwable: Throwable) {
       state =
           state.copy(isLoading = false, error = throwable, favoriteItems = emptyList()).apply {
               postStateValue()
           }
   }
}