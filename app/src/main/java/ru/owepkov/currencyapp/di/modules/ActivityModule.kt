package ru.owepkov.currencyapp.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.owepkov.currencyapp.CurrencyActivity
import ru.owepkov.currencyapp.ui.sort.SortActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun bindCurrencyActivity(): CurrencyActivity

    @ContributesAndroidInjector
    abstract fun bindSortActivity(): SortActivity
}