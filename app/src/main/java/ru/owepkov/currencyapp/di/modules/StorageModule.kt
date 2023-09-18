package ru.owepkov.currencyapp.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.owepkov.currencyapp.BuildConfig
import ru.owepkov.currencyapp.data.models.local.Migrations
import ru.owepkov.currencyapp.data.repository.local.LocalDatabase
import ru.owepkov.currencyapp.domain.SortPreferenceManager
import javax.inject.Singleton

@Module
class StorageModule {
    @Singleton
    @Provides
    fun provideLocalDatabase(context: Context): LocalDatabase {
        val builder = Room.databaseBuilder(context, LocalDatabase::class.java, LocalDatabase.NAME)
            .addMigrations(Migrations.Migration_1_2)

        if (BuildConfig.DEBUG) {
            builder.fallbackToDestructiveMigration()
        }

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Singleton
    @Provides
    fun provideSortPreferenceManager(preferences: SharedPreferences): SortPreferenceManager {
        return SortPreferenceManager(preferences)
    }
}