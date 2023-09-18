package ru.owepkov.currencyapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.recyclerview.widget.LinearLayoutManager
import ru.owepkov.currencyapp.databinding.FragmentFavoriteBinding
import ru.owepkov.currencyapp.ui.base.BaseFragment
import ru.owepkov.currencyapp.ui.favorite.recycler.FavoriteRecyclerViewAdapter
import ru.owepkov.currencyapp.ui.sharecurrencyandfavorite.SharedViewModel
import javax.inject.Inject

class FavoriteFragment @Inject constructor() : BaseFragment<FavoriteViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var sharedViewModel: SharedViewModel

    override val viewModel: FavoriteViewModel by viewModels { viewModelFactory }

    private val recyclerViewAdapter = FavoriteRecyclerViewAdapter {
        viewModel.removeFavorite(it.localId)
        sharedViewModel.onRemoveFavorite(it.localId)
    }

    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        initRecyclerView()
        initListeners()

        return binding.root
    }

    private fun initRecyclerView() {
        binding.favoriteRecycler.layoutManager = LinearLayoutManager(requireActivity())
        binding.favoriteRecycler.adapter = recyclerViewAdapter
    }

    private fun initListeners() {
        viewModel.stateLive.map { it.favoriteItems }.observe {
            recyclerViewAdapter.setData(it)
        }
        viewModel.stateLive.map { it.isLoading }.observe {
            binding.favoriteProgressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        sharedViewModel.stateLive.map { it.needUpdate }.observe {
            viewModel.getFavoriteList()
        }
        viewModel.stateLive.map { it.error }.observe {
            if (it != null) {
                showErrorToast(it)
            }
        }
    }

    /*companion object {
        fun create(sharedViewModel: SharedViewModel): FavoriteFragment {
            return FavoriteFragment().apply {
                this.sharedViewModel = sharedViewModel
            }
        }
    }*/
}