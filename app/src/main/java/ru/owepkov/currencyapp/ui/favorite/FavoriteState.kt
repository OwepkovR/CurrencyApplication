package ru.owepkov.currencyapp.ui.favorite

import ru.owepkov.currencyapp.data.models.ui.FavoriteListItem

data class FavoriteState(
    val isLoading: Boolean = false,
    val favoriteItems: List<FavoriteListItem> = emptyList(),
    val error: Throwable? = null
)