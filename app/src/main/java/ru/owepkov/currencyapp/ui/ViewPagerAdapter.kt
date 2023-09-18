package ru.owepkov.currencyapp.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.owepkov.currencyapp.ui.currency.CurrencyFragment
import ru.owepkov.currencyapp.ui.favorite.FavoriteFragment

class ViewPagerAdapter(
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = ITEMS_COUNT

    override fun createFragment(position: Int): Fragment {
        return when (ViewPagerItems.getByPosition(position)) {
            ViewPagerItems.POPULAR -> CurrencyFragment()
            ViewPagerItems.FAVORITE -> FavoriteFragment()
            else -> throw IllegalStateException("Не существует фрагмента для позиции $position")
        }
    }

    companion object {
        const val ITEMS_COUNT = 2
    }
}