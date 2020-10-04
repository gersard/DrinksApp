package cl.gerardomascayano.drinksapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.gerardomascayano.drinksapp.databinding.RvDrinksMergeBinding

class GridConcatAdapter(private val listDrinksAdapter: ListDrinksAdapter, private val spanCount: Int) :
    RecyclerView.Adapter<GridConcatAdapter.GridConcatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridConcatViewHolder {
        val viewBinding = RvDrinksMergeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        viewBinding.rvDrinks.layoutManager = GridLayoutManager(parent.context, spanCount)
        return GridConcatViewHolder(viewBinding)
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: GridConcatViewHolder, position: Int) {
        holder.bind(listDrinksAdapter)
    }


    inner class GridConcatViewHolder(private val viewBinding: RvDrinksMergeBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(adapter: ListDrinksAdapter) {
            viewBinding.rvDrinks.adapter = adapter
        }
    }


}