package ru.owepkov.currencyapp.ui

import ru.owepkov.currencyapp.R

enum class ViewPagerItems(val position: Int, val titleRes: Int? = null) {
    POPULAR(0, R.string.tab_currencies_title),
    FAVORITE(1, R.string.tab_favorite_title),
    UNDEFINED(-1);

    companion object {
        fun getByPosition(position: Int?) = values().find { it.position == position } ?: UNDEFINED
    }
}