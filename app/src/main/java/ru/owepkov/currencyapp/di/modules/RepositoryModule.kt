package ru.owepkov.currencyapp.di.modules

import dagger.Module
import dagger.Provides
import ru.owepkov.currencyapp.data.repository.CurrencyRepository
import ru.owepkov.currencyapp.data.repository.network.CurrencyRepositoryImpl
import ru.owepkov.currencyapp.data.source.CurrencyLocalDataSource
import ru.owepkov.currencyapp.data.source.CurrencyNetworkDataSource
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideNetworkRepository(
        localDataSource: CurrencyLocalDataSource,
        networkDataSource: CurrencyNetworkDataSource
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(localDataSource, networkDataSource)
    }
}