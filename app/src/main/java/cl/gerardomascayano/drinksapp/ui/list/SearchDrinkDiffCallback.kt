package cl.gerardomascayano.drinksapp.ui.list

import androidx.recyclerview.widget.DiffUtil
import cl.gerardomascayano.drinksapp.domain.model.DrinkSearch

class SearchDrinkDiffCallback(private val oldList: List<DrinkSearch>, private val newList: List<DrinkSearch>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}