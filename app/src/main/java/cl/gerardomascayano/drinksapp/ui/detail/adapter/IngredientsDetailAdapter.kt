package cl.gerardomascayano.drinksapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.gerardomascayano.drinksapp.databinding.ItemIngredientBinding
import cl.gerardomascayano.drinksapp.domain.model.Ingredient

class IngredientsDetailAdapter(private val listIngredients: List<Ingredient>) :
    RecyclerView.Adapter<IngredientsDetailAdapter.IngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = IngredientViewHolder(
        ItemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = listIngredients.size

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) = holder.bindData(listIngredients[position])

    inner class IngredientViewHolder(private val viewBinding: ItemIngredientBinding) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bindData(ingredient: Ingredient) {
            viewBinding.tvIngredientName.text = ingredient.name
        }

    }

}