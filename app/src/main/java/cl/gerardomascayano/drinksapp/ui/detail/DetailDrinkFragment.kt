package cl.gerardomascayano.drinksapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cl.gerardomascayano.drinksapp.R
import cl.gerardomascayano.drinksapp.core.Resource
import cl.gerardomascayano.drinksapp.core.extension.exhaustive
import cl.gerardomascayano.drinksapp.databinding.FragmentDetailDrinkBinding
import cl.gerardomascayano.drinksapp.domain.model.Drink
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailDrinkFragment : Fragment() {

    private var _viewBinding: FragmentDetailDrinkBinding? = null
    private val viewBinding get() = _viewBinding!!
    private val viewModel = viewModels<DetailDrinkFragmentViewModel>()
    private var drinkId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            drinkId = it.getInt(KEY_ID_DRINK)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewBinding = FragmentDetailDrinkBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.value.getDrink(drinkId!!)
    }

    private fun setupObservers() {
        viewModel.value.detailDrinkEvent.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Loading -> TODO()
                is Resource.Success -> setupUi(resource.data)
                is Resource.Empty -> TODO()
                is Resource.Failure -> TODO()
            }.exhaustive
        })
    }

    private fun setupUi(drink: Drink) {
        viewBinding.tvTitle.text = drink.name
        viewBinding.rbRating.rating = drink.rating
        viewBinding.cbFavorite.isChecked = drink.favorite
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    companion object {

        private const val KEY_ID_DRINK = "id_drink"

        @JvmStatic
        fun newInstance(idDrink: Int) =
            DetailDrinkFragment().apply {
                arguments = Bundle().apply { putInt(KEY_ID_DRINK, idDrink) }
            }
    }
}