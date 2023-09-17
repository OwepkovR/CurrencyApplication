package ru.owepkov.currencyapp.data.models.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migrations {
    val Migration_1_2 = object : Migration(1,2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${FavoriteCurrencyEntity.TABLE_NAME} ADD COLUMN amount REAL")
        }
    }
}