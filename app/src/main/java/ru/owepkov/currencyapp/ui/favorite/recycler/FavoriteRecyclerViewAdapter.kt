package ru.owepkov.currencyapp.ui.favorite.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.owepkov.currencyapp.R
import ru.owepkov.currencyapp.data.models.ui.FavoriteListItem
import ru.owepkov.currencyapp.databinding.FavoriteRecyclerItemBinding

class FavoriteRecyclerViewAdapter(
    private val removeFavorite : (item: FavoriteListItem) -> Unit
) : ListAdapter<FavoriteListItem, FavoriteRecyclerViewAdapter.FavoriteViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            FavoriteRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position), removeFavorite)
    }

    fun setData(data: List<FavoriteListItem>) {
        submitList(data)
    }

    class FavoriteViewHolder(
        private val binding: FavoriteRecyclerItemBinding
    ) : ViewHolder(binding.root) {
        fun bind(item: FavoriteListItem, removeFavorite: (item: FavoriteListItem) -> Unit) {
            binding.currencyTitle.text = item.baseCurrency.plus("/").plus(item.secondaryCurrency)
            binding.currencyValue.text = item.amount.toString()
            binding.favoriteButton.setImageResource(R.drawable.favorite_selected)

            binding.favoriteButton.setOnClickListener {
                removeFavorite(item)
            }
        }
    }

    private class DiffUtilCallback : DiffUtil.ItemCallback<FavoriteListItem>() {
        override fun areItemsTheSame(
            oldItem: FavoriteListItem,
            newItem: FavoriteListItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FavoriteListItem,
            newItem: FavoriteListItem
        ): Boolean {
            return oldItem.localId == newItem.localId
        }

    }
}