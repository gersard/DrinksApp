package cl.gerardomascayano.drinksapp.ui.list.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.gerardomascayano.drinksapp.databinding.EtSearchDrinksBinding

class SearchDrinksAdapter : RecyclerView.Adapter<SearchDrinksAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchViewHolder(
        EtSearchDrinksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) = Unit


    inner class SearchViewHolder(private val viewBinding: EtSearchDrinksBinding) : RecyclerView.ViewHolder(viewBinding.root), TextWatcher {

        init {
            viewBinding.etSearchDrinks.addTextChangedListener(this)
        }

        override fun afterTextChanged(s: Editable?) {
            TODO("Not yet implemented")
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

    }

}