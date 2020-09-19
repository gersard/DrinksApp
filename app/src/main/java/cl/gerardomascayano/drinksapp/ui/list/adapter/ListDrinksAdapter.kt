package cl.gerardomascayano.drinksapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.gerardomascayano.drinksapp.core.GlideApp
import cl.gerardomascayano.drinksapp.databinding.ItemDrinkBinding
import cl.gerardomascayano.drinksapp.domain.model.Drink

class ListDrinksAdapter() : RecyclerView.Adapter<ListDrinksAdapter.DrinkListViewHolder>() {

    private var listDrinks: MutableList<Drink> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkListViewHolder {
        return DrinkListViewHolder(ItemDrinkBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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