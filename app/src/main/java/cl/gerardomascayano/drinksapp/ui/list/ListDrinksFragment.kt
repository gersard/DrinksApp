package cl.gerardomascayano.drinksapp.ui.list


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.marginStart
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.gerardomascayano.drinksapp.R
import cl.gerardomascayano.drinksapp.core.OnFragmentBackPressed
import cl.gerardomascayano.drinksapp.core.Resource
import cl.gerardomascayano.drinksapp.core.extension.*
import cl.gerardomascayano.drinksapp.databinding.FragmentListDrinksBinding
import cl.gerardomascayano.drinksapp.domain.model.Drink
import cl.gerardomascayano.drinksapp.ui.detail.DetailDrinkFragment
import cl.gerardomascayano.drinksapp.ui.list.adapter.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@AndroidEntryPoint
class ListDrinksFragment : Fragment(), DrinkItemListener, View.OnTouchListener, OnFragmentBackPressed {

    private val viewModel = viewModels<ListDrinksFragmentViewModel>()
    private var _viewBinding: FragmentListDrinksBinding? = null
    private val viewBinding get() = _viewBinding!!
    private var rvSearchListResults: RecyclerView? = null
    private lateinit var favoriteDrinksAdapter: ListDrinksAdapter
    private lateinit var allDrinksAdapter: ListDrinksAdapter
    private lateinit var wrapperFavoriteDrinksAdapter: LinearConcatAdapter
    private lateinit var searchAdapter: SearchDrinksAdapter
    private lateinit var titleFavoriteAdapter: TitleAdapter
    private lateinit var titleAllAdapter: TitleAdapter
    private var drinksSearchAdapter: ListDrinksSearchAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewBinding = FragmentListDrinksBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupFavoriteObserver()
        setupUnfavoriteObserver()
        setupSearchedObservers()
        viewModel.value.loadData()

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            ListDrinksBackPressedCallback(true, activity, this) { rvSearchListResults != null && rvSearchListResults?.isVisible() == true })

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("ClickableViewAccessibility")
    private fun setupUi() {
        searchAdapter = SearchDrinksAdapter(this)
        titleFavoriteAdapter = TitleAdapter(getString(R.string.title_favorite_drinks))
        titleAllAdapter = TitleAdapter(getString(R.string.title_all_drinks))
        favoriteDrinksAdapter = ListDrinksAdapter(this)
        allDrinksAdapter = ListDrinksAdapter(this, true, requireActivity().screenWidth() - (16.dpToPx() + 16.dpToPx()))

        wrapperFavoriteDrinksAdapter = LinearConcatAdapter(favoriteDrinksAdapter, LinearLayoutManager.HORIZONTAL)
        val gridConcatAdapter = GridConcatAdapter(allDrinksAdapter, 2)
        val mergeAdapter = ConcatAdapter(
            searchAdapter, titleFavoriteAdapter, wrapperFavoriteDrinksAdapter
            , titleAllAdapter, gridConcatAdapter
        )

        viewBinding.rvMain.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.rvMain.adapter = mergeAdapter


//        viewBinding.etSearchDrinks.setOnTouchListener(this)
//        viewModel.value.searchDrinkFlow = viewBinding.etSearchDrinks.textChangeStateFlow()
    }

    private fun setupFavoriteObserver() {
        viewModel.value.listDrinksFavoriteEvent.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                // if (resource.isLoading) showProgressFavoriteDrinks() else hideProgressFavoriteDrinks()
                is Resource.Loading -> Unit
                is Resource.Success -> successFavoriteDrinks(resource.data)
                is Resource.Empty -> Unit
                is Resource.Failure -> Unit
            }.exhaustive
        })
    }

    private fun successFavoriteDrinks(drinks: List<Drink>) {
        favoriteDrinksAdapter.addDrinks(drinks)
        if (viewModel.value.stateFavoriteDrinks != null) {
            wrapperFavoriteDrinksAdapter.layoutInstance = viewModel.value.stateFavoriteDrinks
        }
    }

    @Suppress("IMPLICIT_CAST_TO_ANY")
    private fun setupUnfavoriteObserver() {
        viewModel.value.listDrinksUnFavoriteEvent.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                // if (resource.isLoading) showProgressUnFavoriteDrinks() else hideProgressUnFavoriteDrinks()
                is Resource.Loading -> Unit
                is Resource.Success -> successUnfavorite(resource.data)
                // hideUnfavoritesView()
                is Resource.Empty -> Unit
                is Resource.Failure -> Unit
            }.exhaustive
        })
    }

    private fun successUnfavorite(drinks: List<Drink>) {
        allDrinksAdapter.addDrinks(drinks)
        if (viewModel.value.stateAllDrinks != null) {
            viewBinding.rvMain.layoutManager?.onRestoreInstanceState(viewModel.value.stateAllDrinks)
        }
    }

    private fun setupSearchedObservers() {
        viewModel.value.listDrinksSearched.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Loading -> TODO()
                is Resource.Success -> drinksSearchAdapter!!.setDrinks(resource.data)
                is Resource.Empty -> drinksSearchAdapter!!.setDrinks(listOf())
                is Resource.Failure -> TODO()
            }.exhaustive

        })
    }

//    private fun hideUnfavoritesView() {
//        viewBinding.rvUnfavoriteDrinkList.gone()
//        viewBinding.tvTitleUnfavoriteDrinks.gone()
//    }
//
//    private fun showProgressUnFavoriteDrinks() {
//        viewBinding.rvUnfavoriteDrinkList.gone()
//        viewBinding.pbUnfavoriteDrinkList.visible()
//    }
//
//    private fun hideProgressUnFavoriteDrinks() {
//        viewBinding.rvUnfavoriteDrinkList.visible()
//        viewBinding.pbUnfavoriteDrinkList.gone()
//    }
//
//    private fun showProgressFavoriteDrinks() {
//        viewBinding.rvFavoriteDrinkList.gone()
//        viewBinding.pbFavoriteDrinkList.visible()
//    }
//
//    private fun hideProgressFavoriteDrinks() {
//        viewBinding.rvFavoriteDrinkList.visible()
//        viewBinding.pbFavoriteDrinkList.gone()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }


    override fun drinkItemClickListener(drinkId: Int) {
        viewModel.value.stateAllDrinks = viewBinding.rvMain.layoutManager?.onSaveInstanceState()
        viewModel.value.stateFavoriteDrinks = wrapperFavoriteDrinksAdapter.getStateLayoutManager()
        activity?.let {
            it.supportFragmentManager.commit {
                addToBackStack("")
                replace(R.id.fcv_main, DetailDrinkFragment.newInstance(drinkId))
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (v?.id == searchAdapter.etSearchId && event?.action == MotionEvent.ACTION_UP && rvSearchListResults?.isVisible() != true) {
//            v.changeWidth(v.width, requireActivity().screenWidth() - (32.dpToPx()))
            v.changeMargin(10.dpToPx())
            v.changeElevation(6.dpToPx().toFloat(), 10.dpToPx().toFloat())
            showSearchResultsView(v.height, v.y)
        }
        return v!!.onTouchEvent(event)
    }

    private fun showSearchResultsView(heightEt: Int, yAxisPos: Float) {
        if (rvSearchListResults == null) {
            rvSearchListResults = viewBinding.vsSearchDrinks.inflate() as RecyclerView

            // Eje y + Height of Edit Text + Margin
            rvSearchListResults!!.y = yAxisPos + heightEt + (10.dpToPx())
            rvSearchListResults!!.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                marginStart = 10.dpToPx()
                marginEnd = 10.dpToPx()
            }

            rvSearchListResults!!.layoutManager = LinearLayoutManager(requireContext())
            this.drinksSearchAdapter = ListDrinksSearchAdapter(this)
            rvSearchListResults!!.adapter = drinksSearchAdapter

            viewModel.value.searchDrinkFlow = searchAdapter.stateFlow
        }
        rvSearchListResults!!.visible(true)
    }

    private fun hideSearchResultsView() {
        rvSearchListResults?.gone(true)
    }

    override fun onFragmentBackPressed() {
        val focusedView = view?.findFocus()
        if (focusedView is EditText) {
            focusedView.changeMargin(20.dpToPx())
            focusedView.changeElevation(10.dpToPx().toFloat(), 6.dpToPx().toFloat())
            hideSearchResultsView()
            viewBinding.root.requestLayout()
        }

    }

}