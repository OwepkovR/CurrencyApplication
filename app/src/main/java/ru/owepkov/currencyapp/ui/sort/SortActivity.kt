package ru.owepkov.currencyapp.ui.sort

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import dagger.android.AndroidInjection
import ru.owepkov.currencyapp.R
import ru.owepkov.currencyapp.data.models.ui.SortDirection
import ru.owepkov.currencyapp.databinding.ActivitySortBinding
import javax.inject.Inject

class SortActivity @Inject constructor() : AppCompatActivity() {
    private lateinit var binding: ActivitySortBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SortViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        binding = ActivitySortBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()

        initSortParams()
        initClearButton()
        initListeners()
        loadSortParams()
    }

    private fun initListeners() {
        viewModel.stateLive.map { it.alphabetSort }.observe(this) {
            if (it != null) {
                when (it) {
                    SortDirection.ASC -> binding.alphabetSortRadiogroup.check(R.id.alphabet_sort_ask)
                    SortDirection.DESC -> binding.alphabetSortRadiogroup.check(R.id.alphabet_sort_desc)
                }
            }
        }
        viewModel.stateLive.map { it.currencySort }.observe(this) {
            if (it != null) {
                when (it) {
                    SortDirection.ASC -> binding.currencySortRadiogroup.check(R.id.currency_sort_ask)
                    SortDirection.DESC -> binding.currencySortRadiogroup.check(R.id.currency_sort_desc)
                }
            }
        }
    }

    private fun loadSortParams() {
        viewModel.loadSortParams()
    }

    private fun initSortParams() {
        binding.alphabetSortRadiogroup.setOnCheckedChangeListener { group, checkedId ->
            var alphabetSortDirection: SortDirection? = null

            when (checkedId) {
                R.id.alphabet_sort_ask -> {
                    alphabetSortDirection = SortDirection.ASC
                }

                R.id.alphabet_sort_desc -> {
                    alphabetSortDirection = SortDirection.DESC
                }
            }

            viewModel.setAlphabetSortDirection(alphabetSortDirection)
        }
        binding.currencySortRadiogroup.setOnCheckedChangeListener { group, checkedId ->
            var currencySortDirection: SortDirection? = null

            when (checkedId) {
                R.id.currency_sort_ask -> {
                    currencySortDirection = SortDirection.ASC
                }

                R.id.currency_sort_desc -> {
                    currencySortDirection = SortDirection.DESC
                }
            }

            viewModel.setCurrencySortDirection(currencySortDirection)
        }
    }

    private fun initClearButton() {
        binding.clearSortButton.setOnClickListener {
            binding.currencySortRadiogroup.clearCheck()
            binding.alphabetSortRadiogroup.clearCheck()
        }
    }

    private fun setupToolbar() {
        val toolbar = binding.toolbar

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SortActivity::class.java)
        }
    }
}