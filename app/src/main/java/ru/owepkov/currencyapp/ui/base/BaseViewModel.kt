package ru.owepkov.currencyapp.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel<S> : ViewModel() {
    protected abstract var state: S

    private val _stateLive = MutableLiveData<S>()
    val stateLive: LiveData<S> = _stateLive

    fun S.setStateValue() {
        val state = this
        viewModelScope.launch {
            _stateLive.value = state
        }
    }

    fun S.postStateValue() {
        val state = this
        viewModelScope.launch(Dispatchers.IO) {
            _stateLive.postValue(state)
        }
    }

    fun start() {
        state.postStateValue()
    }
}