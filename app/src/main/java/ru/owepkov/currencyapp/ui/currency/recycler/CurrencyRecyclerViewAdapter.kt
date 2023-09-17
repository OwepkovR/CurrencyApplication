package ru.owepkov.currencyapp.ui.currency.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.owepkov.currencyapp.R
import ru.owepkov.currencyapp.data.models.ui.CurrencyListItem
import ru.owepkov.currencyapp.databinding.CurrencyRecyclerItemBinding

class CurrencyRecyclerViewAdapter(private val onFavoriteClick: (item: CurrencyListItem) -> Unit) :
    ListAdapter<CurrencyListItem, CurrencyRecyclerViewAdapter.CurrencyViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CurrencyViewHolder(
        CurrencyRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        onFavoriteClick
    )

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setData(list: List<CurrencyListItem>) {
        submitList(list)
    }

    class CurrencyViewHolder(
        private val binding: CurrencyRecyclerItemBinding,
        private val onFavoriteClick: (item: CurrencyListItem) -> Unit
    ) : ViewHolder(binding.root) {
        fun bind(data: CurrencyListItem) {
            binding.currencyTitle.text = data.title
            binding.currencyValue.text = data.currency.toString()

            if (data.isFavorite) {
                binding.favoriteButton.setImageResource(R.drawable.favorite_selected)
            } else {
                binding.favoriteButton.setImageResource(R.drawable.favorite_unselected)
            }

            binding.favoriteButton.setOnClickListener {
                onFavoriteClick(data)
            }
        }
    }

    private class DiffUtilCallback : DiffUtil.ItemCallback<CurrencyListItem>() {

        override fun areItemsTheSame(
            oldItem: CurrencyListItem,
            newItem: CurrencyListItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CurrencyListItem,
            newItem: CurrencyListItem
        ): Boolean {
            return oldItem.isFavorite == newItem.isFavorite
                    && oldItem.currency == newItem.currency
                    && oldItem.title == newItem.title
        }
    }
}