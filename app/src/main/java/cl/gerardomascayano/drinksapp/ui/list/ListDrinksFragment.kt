package cl.gerardomascayano.drinksapp.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import cl.gerardomascayano.drinksapp.R
import cl.gerardomascayano.drinksapp.databinding.FragmentListDrinksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListDrinksFragment : Fragment() {

    private val viewModel = viewModels<ListDrinksFragmentViewModel>()
    private var _viewBinding: FragmentListDrinksBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = FragmentListDrinksBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.value.loadData()
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