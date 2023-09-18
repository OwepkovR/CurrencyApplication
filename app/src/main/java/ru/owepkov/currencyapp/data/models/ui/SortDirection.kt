package ru.owepkov.currencyapp.data.models.ui

enum class SortDirection(val stringCode: String) {
    ASC("ASC"),
    DESC("DESC");

    companion object {
        fun from(s: String?) = values().find { it.stringCode == s }
    }
}