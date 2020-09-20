package cl.gerardomascayano.drinksapp.ui.list

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cl.gerardomascayano.drinksapp.core.Resource
import cl.gerardomascayano.drinksapp.core.extension.exhaustive
import cl.gerardomascayano.drinksapp.core.extension.gone
import cl.gerardomascayano.drinksapp.core.extension.visible
import cl.gerardomascayano.drinksapp.databinding.FragmentListDrinksBinding
import cl.gerardomascayano.drinksapp.domain.model.Drink
import cl.gerardomascayano.drinksapp.ui.list.adapter.ListDrinksAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class ListDrinksFragment : Fragment(), DrinkItemListener {

    private val viewModel = viewModels<ListDrinksFragmentViewModel>()
    private var _viewBinding: FragmentListDrinksBinding? = null
    private val viewBinding get() = _viewBinding!!
    private lateinit var favoriteAdapterDrinks: ListDrinksAdapter
    private lateinit var unFavoriteAdapterDrinks: ListDrinksAdapter

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
        setupUnfavoriteObserver()
        viewModel.value.loadData()
    }

    private fun setupUi() {
        viewBinding.rvFavoriteDrinkList.setHasFixedSize(true)
        viewBinding.rvFavoriteDrinkList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        favoriteAdapterDrinks = ListDrinksAdapter(this)
        viewBinding.rvFavoriteDrinkList.adapter = favoriteAdapterDrinks

        viewBinding.rvUnfavoriteDrinkList.layoutManager = GridLayoutManager(context, 2)
        viewBinding.rvUnfavoriteDrinkList.post {
            val marginStart = viewBinding.rvUnfavoriteDrinkList.marginStart
            val marginEnd = viewBinding.rvUnfavoriteDrinkList.marginEnd
            unFavoriteAdapterDrinks = ListDrinksAdapter(this,true, screenWidth() - (marginStart + marginEnd))
            viewBinding.rvUnfavoriteDrinkList.adapter = unFavoriteAdapterDrinks
        }
    }

    private fun setupFavoriteObserver() {
        viewModel.value.listDrinksFavoriteEvent.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Loading -> if (resource.isLoading) showProgressFavoriteDrinks() else hideProgressFavoriteDrinks()
                is Resource.Success -> favoriteAdapterDrinks.addDrinks(resource.data)
                is Resource.Empty -> Unit
                is Resource.Failure -> Unit
            }.exhaustive
        })
    }

    private fun setupUnfavoriteObserver() {
        viewModel.value.listDrinksUnFavoriteEvent.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Loading -> if (resource.isLoading) showProgressUnFavoriteDrinks() else hideProgressUnFavoriteDrinks()
                is Resource.Success -> unFavoriteAdapterDrinks.addDrinks(resource.data)
                is Resource.Empty -> hideUnfavoritesView()
                is Resource.Failure -> Unit
            }.exhaustive
        })
    }

    private fun hideUnfavoritesView() {
        viewBinding.rvUnfavoriteDrinkList.gone()
        viewBinding.tvTitleUnfavoriteDrinks.gone()
    }

    private fun showProgressUnFavoriteDrinks(){
        viewBinding.rvUnfavoriteDrinkList.gone()
        viewBinding.pbUnfavoriteDrinkList.visible()
    }

    private fun hideProgressUnFavoriteDrinks(){
        viewBinding.rvUnfavoriteDrinkList.visible()
        viewBinding.pbUnfavoriteDrinkList.gone()
    }

    private fun showProgressFavoriteDrinks(){
        viewBinding.rvFavoriteDrinkList.gone()
        viewBinding.pbFavoriteDrinkList.visible()
    }

    private fun hideProgressFavoriteDrinks(){
        viewBinding.rvFavoriteDrinkList.visible()
        viewBinding.pbFavoriteDrinkList.gone()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private fun screenWidth(): Int {
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    override fun drinkItemClickListener(drink: Drink) {
        Timber.d("Drink clicked: $drink")
    }
}