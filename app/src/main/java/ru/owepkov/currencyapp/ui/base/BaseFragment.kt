package ru.owepkov.currencyapp.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import dagger.android.support.AndroidSupportInjection
import ru.owepkov.currencyapp.R
import ru.owepkov.currencyapp.data.models.network.ApiException
import ru.owepkov.currencyapp.data.models.network.ApiExceptionType

abstract class BaseFragment<out VM: BaseViewModel<*>> : Fragment() {
    abstract val viewModel: VM

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.start()
    }

    @MainThread
    protected inline fun <T> LiveData<T>.observe(crossinline onChanged: (T) -> Unit) {
        observe(owner = viewLifecycleOwner, onChanged = onChanged)
    }

    protected fun showErrorToast(throwable: Throwable) {
        val resId: Int = if (throwable is ApiException) {
            when (ApiExceptionType.from(throwable.code)) {
                ApiExceptionType.SUBSCRIPTION_PLAN_ERROR -> {
                    R.string.error_subscription_plan
                }
                else -> {
                    R.string.unknown_error
                }
            }
        } else {
            R.string.unknown_error
        }

        Toast.makeText(requireActivity(), getString(resId), Toast.LENGTH_LONG).show()
    }
}