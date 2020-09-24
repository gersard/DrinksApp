package cl.gerardomascayano.drinksapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.gerardomascayano.drinksapp.core.extension.loadImage
import cl.gerardomascayano.drinksapp.databinding.ItemDrinkSearchBinding
import cl.gerardomascayano.drinksapp.domain.model.DrinkSearch
import cl.gerardomascayano.drinksapp.ui.list.DrinkItemListener

class ListDrinksSearchAdapter(private val drinkListener: DrinkItemListener) : RecyclerView.Adapter<ListDrinksSearchAdapter.DrinkSearchViewHolder>() {

    private var drinksSearch: List<DrinkSearch> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DrinkSearchViewHolder(
        ItemDrinkSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = drinksSearch.size

    override fun onBindViewHolder(holder: DrinkSearchViewHolder, position: Int) = holder.bindDrink(drinksSearch[position])

    fun setDrinks(drinks: List<DrinkSearch>){
        this.drinksSearch = drinks
        notifyDataSetChanged()
    }

    inner class DrinkSearchViewHolder(private val viewBinding: ItemDrinkSearchBinding) : RecyclerView.ViewHolder(viewBinding.root),
        View.OnClickListener {

        init {
            viewBinding.root.setOnClickListener(this)
        }

        fun bindDrink(drink: DrinkSearch) {
            viewBinding.tvDrinkName.text = drink.name
            viewBinding.ivDrinkImage.loadImage(drink.imageUrl)
        }

        override fun onClick(v: View?) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                drinkListener.drinkItemClickListener(drinksSearch[adapterPosition].id)
            }
        }

    }

}