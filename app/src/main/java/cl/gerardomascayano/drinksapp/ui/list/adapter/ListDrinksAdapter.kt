package cl.gerardomascayano.drinksapp.ui.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.gerardomascayano.drinksapp.core.GlideApp
import cl.gerardomascayano.drinksapp.core.extension.loadImage
import cl.gerardomascayano.drinksapp.databinding.ItemDrinkBinding
import cl.gerardomascayano.drinksapp.domain.model.Drink
import cl.gerardomascayano.drinksapp.ui.list.DrinkItemListener
import timber.log.Timber

class ListDrinksAdapter(private val drinkListener: DrinkItemListener, private val useGridLayoutManager: Boolean = false, screenWidth: Int = 0) :
    RecyclerView.Adapter<ListDrinksAdapter.DrinkListViewHolder>() {

    private var listDrinks: MutableList<Drink> = mutableListOf()
    private var squareSize: Int? = null

    init {
        if (useGridLayoutManager) {
            squareSize = screenWidth / 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkListViewHolder {
        val viewBinding = ItemDrinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        if (useGridLayoutManager) {
            val lp = viewBinding.root.layoutParams
            lp.width = squareSize!!
            lp.height = squareSize!!
            viewBinding.root.layoutParams = lp

            val imageLp = viewBinding.ivDrinkImage.layoutParams
            imageLp.height = squareSize!!
            viewBinding.ivDrinkImage.layoutParams = imageLp
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

    inner class DrinkListViewHolder(private val viewBinding: ItemDrinkBinding) : RecyclerView.ViewHolder(viewBinding.root), View.OnClickListener {

        init {
            viewBinding.root.setOnClickListener(this)
        }

        fun bindDrink(drink: Drink) {
            viewBinding.tvDrinkTitle.text = drink.name
            viewBinding.ivDrinkImage.loadImage(drink.imageUrl)
        }

        override fun onClick(v: View?) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                drinkListener.drinkItemClickListener(listDrinks[adapterPosition].id)
            }
        }

    }
}