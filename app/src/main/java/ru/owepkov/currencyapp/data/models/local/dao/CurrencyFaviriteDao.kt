package ru.owepkov.currencyapp.data.models.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.owepkov.currencyapp.data.models.local.FavoriteCurrencyEntity

@Dao
interface CurrencyFaviriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: FavoriteCurrencyEntity) : Long

    @Query("SELECT * FROM FavoriteCurrencyEntity where baseCurrency = :currency or secondaryCurrency = :currency")
    fun getFavoritesByCurrency(currency: String) : List<FavoriteCurrencyEntity>

    @Query("DELETE from FavoriteCurrencyEntity where localId = :localId")
    fun removeFavorite(localId: Long?)

    @Query("SELECT * FROM FavoriteCurrencyEntity")
    fun selectAll() : List<FavoriteCurrencyEntity>
}