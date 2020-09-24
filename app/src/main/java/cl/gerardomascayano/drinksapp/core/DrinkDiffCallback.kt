package cl.gerardomascayano.drinksapp.core

import androidx.recyclerview.widget.DiffUtil

class DrinkDiffCallback<T : DiffUtilHelperModel>(private val oldList: List<T>, private val newList: List<T>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].getIdentifier() == newList[newItemPosition].getIdentifier()
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}