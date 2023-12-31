package ru.owepkov.currencyapp.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.owepkov.currencyapp.data.models.local.FavoriteCurrencyEntity
import ru.owepkov.currencyapp.data.models.local.dao.CurrencyFavoriteDao

@Database(entities = [FavoriteCurrencyEntity::class], version = 2)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getCurrencyFavoriteDao(): CurrencyFavoriteDao

    companion object {
        const val NAME = "local_database.db"
    }
}