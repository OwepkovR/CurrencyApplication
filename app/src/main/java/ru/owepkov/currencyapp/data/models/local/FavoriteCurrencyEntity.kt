package ru.owepkov.currencyapp.data.models.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = FavoriteCurrencyEntity.TABLE_NAME)
data class FavoriteCurrencyEntity(
    @PrimaryKey(autoGenerate = true)
    val localId: Long = 0,
    val baseCurrency: String,
    val secondaryCurrency: String,
    val amount: Float
) {
    companion object {
        const val TABLE_NAME = "FavoriteCurrencyEntity"
    }
}