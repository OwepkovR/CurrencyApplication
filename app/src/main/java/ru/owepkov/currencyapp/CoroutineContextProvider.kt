package ru.owepkov.currencyapp

import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {
    /**
     * для операций на UI потоке
     */
    val Main: CoroutineContext

    /**
     * для операций в фоне, которым нужна мощность процессора
     */
    val Default: CoroutineContext

    /**
     * для небольших операций в фоне, например сеть
     */
    val IO : CoroutineContext

    /**
     * мейн поток, операция выполняется без очереди
     */
    val MainImmediate : CoroutineContext
}