package ru.owepkov.currencyapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.owepkov.currencyapp.ui.favorite.FavoriteFragment
import ru.owepkov.currencyapp.ui.currency.CurrencyFragment
import ru.owepkov.currencyapp.ui.sharecurrencyandfavorite.SharedViewModel

class ViewPagerAdapter(
    activity: FragmentActivity,
    private val sharedViewModel: SharedViewModel
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = ITEMS_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (ViewPagerItems.getByPosition(position)) {
            ViewPagerItems.POPULAR -> CurrencyFragment(sharedViewModel)
            ViewPagerItems.FAVORITE -> FavoriteFragment(sharedViewModel)
            else -> throw IllegalStateException("Не существует фрагмента для позиции $position")
        }
    }

    companion object {
        const val ITEMS_COUNT = 2
    }
}