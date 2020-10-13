package cl.gerardomascayano.drinksapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.gerardomascayano.drinksapp.core.extension.textChangeStateFlow
import cl.gerardomascayano.drinksapp.databinding.EtSearchDrinksBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
class SearchDrinksAdapter(private val touchListener: View.OnTouchListener) : RecyclerView.Adapter<SearchDrinksAdapter.SearchViewHolder>() {

    var etSearchId: Int = -1
    var stateFlow: StateFlow<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val viewBinding = EtSearchDrinksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        etSearchId = viewBinding.etSearchDrinks.id
        return SearchViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) = Unit


    inner class SearchViewHolder(private val viewBinding: EtSearchDrinksBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        init {
            viewBinding.etSearchDrinks.setOnTouchListener(touchListener)
            stateFlow = viewBinding.etSearchDrinks.textChangeStateFlow()
        }

    }

}