package ru.owepkov.currencyapp.di.modules

import dagger.Module
import dagger.Provides
import ru.owepkov.currencyapp.CoroutineContextProvider
import ru.owepkov.currencyapp.CoroutineContextProviderImpl
import javax.inject.Singleton

@Module
class CoroutineContextProviderModule {
    @Provides
    @Singleton
    fun provideCoroutineContextProvider(): CoroutineContextProvider {
        return CoroutineContextProviderImpl()
    }
}