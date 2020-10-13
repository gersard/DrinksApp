package cl.gerardomascayano.drinksapp.ui.list.adapter

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.gerardomascayano.drinksapp.databinding.RvDrinksMergeBinding

class LinearConcatAdapter(private val listDrinksAdapter: ListDrinksAdapter, private val orientation: Int) :
    RecyclerView.Adapter<LinearConcatAdapter.LinearConcatViewHolder>() {

    private lateinit var layoutManager: LinearLayoutManager
    var layoutInstance: Parcelable? = null
        set(value) {
            field = value
//            notifyItemChanged(0)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinearConcatViewHolder {
        val viewBinding = RvDrinksMergeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        layoutManager = LinearLayoutManager(parent.context, orientation, false)
        viewBinding.rvDrinks.layoutManager = layoutManager
        return LinearConcatViewHolder(viewBinding)
    }

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: LinearConcatViewHolder, position: Int) {
        holder.bind(listDrinksAdapter)
    }

    fun getStateLayoutManager(): Parcelable? {
        return layoutManager.onSaveInstanceState()
    }

    inner class LinearConcatViewHolder(private val viewBinding: RvDrinksMergeBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(adapter: ListDrinksAdapter) {
            viewBinding.rvDrinks.adapter = adapter
            if (layoutInstance != null) {
                viewBinding.rvDrinks.layoutManager?.onRestoreInstanceState(layoutInstance)
                layoutInstance = null
            }
        }
    }


}