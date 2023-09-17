package ru.owepkov.currencyapp.di.modules

import dagger.Module
import dagger.Provides
import ru.owepkov.currencyapp.data.repository.network.CurrencyApi
import ru.owepkov.currencyapp.data.source.CurrencyNetworkDataSource
import ru.owepkov.currencyapp.data.source.network.CurrencyNetworkDataSourceImpl
import javax.inject.Singleton

@Module
class NetworkDataSourceModule {
    @Provides
    @Singleton
    fun provideCurrencyNetworkDataSource(api: CurrencyApi): CurrencyNetworkDataSource {
        return CurrencyNetworkDataSourceImpl(api)
    }
}