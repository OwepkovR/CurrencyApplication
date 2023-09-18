package ru.owepkov.currencyapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.owepkov.currencyapp.di.ViewModelKey
import ru.owepkov.currencyapp.ui.currency.CurrencyViewModel
import ru.owepkov.currencyapp.ui.favorite.FavoriteViewModel
import ru.owepkov.currencyapp.ui.sharecurrencyandfavorite.SharedViewModel
import ru.owepkov.currencyapp.ui.sort.SortViewModel
import ru.owepkov.currencyapp.utils.ViewModelFactory

@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(CurrencyViewModel::class)
    abstract fun bindCurrencyViewModel(viewModel: CurrencyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    abstract fun bindSharedViewModel(viewModel: SharedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SortViewModel::class)
    abstract fun bindSortViewModel(viewModel: SortViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}