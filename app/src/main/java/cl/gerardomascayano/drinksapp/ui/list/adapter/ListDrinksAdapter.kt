package cl.gerardomascayano.drinksapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.gerardomascayano.drinksapp.core.GlideApp
import cl.gerardomascayano.drinksapp.databinding.ItemDrinkBinding
import cl.gerardomascayano.drinksapp.domain.model.Drink
import timber.log.Timber

class ListDrinksAdapter(private val useGridLayoutManager: Boolean = false, screenWidth: Int = 0) : RecyclerView.Adapter<ListDrinksAdapter.DrinkListViewHolder>() {

    private var listDrinks: MutableList<Drink> = mutableListOf()
    private var squareSize: Int? = null

    init {
        if (useGridLayoutManager) {
            squareSize = screenWidth / 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkListViewHolder {
        val viewBinding = ItemDrinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        if (useGridLayoutManager){
            val lp = viewBinding.root.layoutParams
            lp.width = squareSize!!
            lp.height = squareSize!!
            viewBinding.root.layoutParams = lp
        }
        return DrinkListViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = listDrinks.size

    override fun onBindViewHolder(holder: DrinkListViewHolder, position: Int) {
        holder.bindDrink(listDrinks[position])
    }

    fun addDrinks(drinks: List<Drink>) {
        val prevSize = listDrinks.size
        listDrinks.addAll(drinks)
        notifyItemRangeInserted(prevSize, listDrinks.size)
    }

    inner class DrinkListViewHolder(private val viewBinding: ItemDrinkBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bindDrink(drink: Drink) {
            viewBinding.tvDrinkTitle.text = drink.name
            GlideApp.with(viewBinding.root.context)
                .load(drink.imageUrl)
                .centerCrop()
//                .placeholder()
//                .error()
                .into(viewBinding.ivDrinkImage)
        }

    }
}