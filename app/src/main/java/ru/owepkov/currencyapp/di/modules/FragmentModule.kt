package ru.owepkov.currencyapp.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.owepkov.currencyapp.ui.currency.CurrencyFragment
import ru.owepkov.currencyapp.ui.favorite.FavoriteFragment

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun bindPopularFragment(): CurrencyFragment

    @ContributesAndroidInjector
    abstract fun bindFavoriteFragment(): FavoriteFragment
}