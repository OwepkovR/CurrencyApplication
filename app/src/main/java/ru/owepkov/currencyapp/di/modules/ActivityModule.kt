package ru.owepkov.currencyapp.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.owepkov.currencyapp.CurrencyActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun bindCurrencyActivity(): CurrencyActivity
}