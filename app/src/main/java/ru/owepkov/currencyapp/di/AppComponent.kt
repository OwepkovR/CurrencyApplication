package ru.owepkov.currencyapp.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.owepkov.currencyapp.App
import ru.owepkov.currencyapp.di.modules.ActivityModule
import ru.owepkov.currencyapp.di.modules.AppModule
import ru.owepkov.currencyapp.di.modules.CoroutineContextProviderModule
import ru.owepkov.currencyapp.di.modules.FragmentModule
import ru.owepkov.currencyapp.di.modules.LocalDataSourceModule
import ru.owepkov.currencyapp.di.modules.NetworkDataSourceModule
import ru.owepkov.currencyapp.di.modules.NetworkModule
import ru.owepkov.currencyapp.di.modules.RepositoryModule
import ru.owepkov.currencyapp.di.modules.StorageModule
import ru.owepkov.currencyapp.di.modules.ViewModelsModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelsModule::class,
        ActivityModule::class,
        FragmentModule::class,
        CoroutineContextProviderModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        NetworkDataSourceModule::class,
        LocalDataSourceModule::class,
        AppModule::class,
        StorageModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}