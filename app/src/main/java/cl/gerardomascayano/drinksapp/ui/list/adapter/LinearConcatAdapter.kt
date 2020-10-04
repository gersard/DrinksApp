package cl.gerardomascayano.drinksapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.gerardomascayano.drinksapp.databinding.RvDrinksMergeBinding

class LinearConcatAdapter(private val listDrinksAdapter: ListDrinksAdapter, private val orientation: Int) :
    RecyclerView.Adapter<LinearConcatAdapter.LinearConcatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinearConcatViewHolder {
        val viewBinding = RvDrinksMergeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        viewBinding.rvDrinks.layoutManager = LinearLayoutManager(parent.context, orientation, false)
        return LinearConcatViewHolder(viewBinding)
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: LinearConcatViewHolder, position: Int) {
        holder.bind(listDrinksAdapter)
    }


    inner class LinearConcatViewHolder(private val viewBinding: RvDrinksMergeBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(adapter: ListDrinksAdapter) {
            viewBinding.rvDrinks.adapter = adapter
        }
    }


}