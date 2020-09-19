package cl.gerardomascayano.drinksapp.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import cl.gerardomascayano.drinksapp.R
import cl.gerardomascayano.drinksapp.core.Resource
import cl.gerardomascayano.drinksapp.core.extension.exhaustive
import cl.gerardomascayano.drinksapp.databinding.FragmentListDrinksBinding
import cl.gerardomascayano.drinksapp.ui.list.adapter.ListDrinksAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListDrinksFragment : Fragment() {

    private val viewModel = viewModels<ListDrinksFragmentViewModel>()
    private var _viewBinding: FragmentListDrinksBinding? = null
    private val viewBinding get() = _viewBinding!!
    private val favoriteAdapterDrinks by lazy { ListDrinksAdapter() }
    private val unFavoriteAdapterDrinks by lazy { ListDrinksAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentListDrinksBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupFavoriteObserver()
        viewModel.value.loadData()
    }

    private fun setupUi() {
        viewBinding.rvFavoriteDrinkList.setHasFixedSize(true)
        viewBinding.rvFavoriteDrinkList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        viewBinding.rvFavoriteDrinkList.adapter = favoriteAdapterDrinks
    }

    private fun setupFavoriteObserver() {
        viewModel.value.listDrinksUnFavoriteEvent.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    favoriteAdapterDrinks.addDrinks(resource.data)
                }
                is Resource.Empty -> Unit
                is Resource.Failure -> Unit
            }.exhaustive
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListDrinksFragment()
    }
}