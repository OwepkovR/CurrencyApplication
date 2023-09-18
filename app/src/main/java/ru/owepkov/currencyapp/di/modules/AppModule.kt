package ru.owepkov.currencyapp.di.modules

import android.content.Context
import dagger.Binds
import dagger.Module
import ru.owepkov.currencyapp.App

@Module
abstract class AppModule {
    @Binds
    abstract fun bindAppContext(app: App): Context
}