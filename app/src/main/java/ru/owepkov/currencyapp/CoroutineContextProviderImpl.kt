package ru.owepkov.currencyapp

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class CoroutineContextProviderImpl : CoroutineContextProvider {
    override val Main: CoroutineContext = Dispatchers.Main
    override val Default: CoroutineContext = Dispatchers.Default
    override val IO: CoroutineContext = Dispatchers.IO
    override val MainImmediate: CoroutineContext = Dispatchers.Main.immediate
}