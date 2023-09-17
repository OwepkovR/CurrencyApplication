package ru.owepkov.currencyapp

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ru.owepkov.currencyapp.di.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}