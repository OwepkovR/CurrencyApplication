package ru.owepkov.currencyapp.ui.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.recyclerview.widget.LinearLayoutManager
import ru.owepkov.currencyapp.R
import ru.owepkov.currencyapp.data.models.network.ApiException
import ru.owepkov.currencyapp.data.models.network.ApiExceptionType
import ru.owepkov.currencyapp.ui.base.BaseFragment
import ru.owepkov.currencyapp.databinding.FragmentCurrencyBinding
import ru.owepkov.currencyapp.ui.currency.recycler.CurrencyRecyclerViewAdapter
import ru.owepkov.currencyapp.ui.sharecurrencyandfavorite.SharedViewModel
import javax.inject.Inject

class CurrencyFragment @Inject constructor(
    private val sharedViewModel: SharedViewModel
) : BaseFragment<CurrencyViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: CurrencyViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentCurrencyBinding

    private val currencyRecyclerViewAdapter = CurrencyRecyclerViewAdapter {
        viewModel.onFavoriteClick(it, binding.currencySpinner.selectedItem as String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCurrencyBinding.inflate(inflater, container, false)

        initSpinner()
        initSortButton()
        initRecyclerView()

        listenState()

        return binding.root
    }

    private fun initRecyclerView() {
        binding.currencyRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.currencyRecycler.adapter = currencyRecyclerViewAdapter
    }

    private fun initSpinner() {
        val adapter = ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.currency_spinner_items,
            android.R.layout.simple_spinner_item
        )

        binding.currencySpinner.adapter = adapter

        binding.currencySpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.getCurrencyList(binding.currencySpinner.selectedItem as? String?)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.getCurrencyList(binding.currencySpinner.selectedItem as? String?)
            }
        }
    }

    private fun initSortButton() {
        binding.sortButton.setOnClickListener {
            //viewModel.testUseCase()
        }
    }

    private fun listenState() {
        viewModel.stateLive.map { it.isLoading }.observe {
            binding.currencyProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.stateLive.map { it.currencyList }.observe {
            currencyRecyclerViewAdapter.setData(it)
            sharedViewModel.onCurrencyStateChange()
        }
        viewModel.stateLive.map { it.error }.observe {
            if (it != null) {
                showErrorToast(it)
            }
        }
        sharedViewModel.stateLive.map { it.updateCurrencyRecordId }.observe {
            if (it > 0) {
                viewModel.unselectFavorite(it)
            }
        }
    }
}