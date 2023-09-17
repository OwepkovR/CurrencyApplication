package ru.owepkov.currencyapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.AndroidInjection
import ru.owepkov.currencyapp.databinding.ActivityMainBinding
import ru.owepkov.currencyapp.ui.ViewPagerAdapter
import ru.owepkov.currencyapp.ui.ViewPagerItems
import ru.owepkov.currencyapp.ui.sharecurrencyandfavorite.SharedViewModel
import ru.owepkov.currencyapp.utils.ViewModelFactory
import javax.inject.Inject

class CurrencyActivity @Inject constructor() : FragmentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val sharedViewModel : SharedViewModel by viewModels { viewModelFactory }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewPager()
    }

    private fun initViewPager() {
        binding.viewPager.adapter = ViewPagerAdapter(this, sharedViewModel)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = getString(ViewPagerItems.getByPosition(position).titleRes ?: R.string.tab_unknown)
        }.attach()
    }
}