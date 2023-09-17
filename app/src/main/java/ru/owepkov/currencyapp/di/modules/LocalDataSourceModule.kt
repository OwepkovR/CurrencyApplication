package ru.owepkov.currencyapp.di.modules

import dagger.Module
import dagger.Provides
import ru.owepkov.currencyapp.data.repository.local.LocalDatabase
import ru.owepkov.currencyapp.data.source.CurrencyLocalDataSource
import ru.owepkov.currencyapp.data.source.local.CurrencyLocalDataSourceImpl
import javax.inject.Singleton

@Module
class LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideCurrencyLocalDataSource(dataBase: LocalDatabase): CurrencyLocalDataSource {
        return CurrencyLocalDataSourceImpl(dataBase)
    }
}