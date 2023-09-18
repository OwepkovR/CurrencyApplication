package ru.owepkov.currencyapp.domain

import android.content.SharedPreferences
import ru.owepkov.currencyapp.data.models.ui.SortDirection

class SortPreferenceManager(
    private val preferences: SharedPreferences
) {
    fun getAlphabetSort(): SortDirection? {
        val stringSort = preferences.getString(ALPHABET_SORT, null)

        return SortDirection.from(stringSort)
    }

    fun getCurrencySort(): SortDirection? {
        val stringSort = preferences.getString(CURRENCY_SORT, null)

        return SortDirection.from(stringSort)
    }

    fun setPreferences(type: String, value: String?) {
        preferences.edit().putString(type, value).apply()
    }

    companion object {
        const val ALPHABET_SORT = "alphabet_sort"
        const val CURRENCY_SORT = "currency_sort"
    }
}