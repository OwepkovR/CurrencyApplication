package ru.owepkov.currencyapp.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.owepkov.currencyapp.BuildConfig
import ru.owepkov.currencyapp.data.models.local.Migrations
import ru.owepkov.currencyapp.data.repository.local.LocalDatabase
import javax.inject.Singleton

@Module
class StorageModule {
    @Singleton
    @Provides
    fun provideLocalDatabase(context: Context): LocalDatabase {
        val builder =  Room.databaseBuilder(context, LocalDatabase::class.java, LocalDatabase.NAME)
            .addMigrations(Migrations.Migration_1_2)

        if (BuildConfig.DEBUG) {
            builder.fallbackToDestructiveMigration()
        }

        return builder.build()
    }
}