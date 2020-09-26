package cl.gerardomascayano.drinksapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.gerardomascayano.drinksapp.R
import cl.gerardomascayano.drinksapp.databinding.FragmentDetailDrinkBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailDrinkFragment : Fragment() {

    private var _viewBinding: FragmentDetailDrinkBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _viewBinding = FragmentDetailDrinkBinding.inflate(inflater, container, false)
        return viewBinding.root
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