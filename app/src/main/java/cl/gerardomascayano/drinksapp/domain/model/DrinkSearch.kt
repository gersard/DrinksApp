package cl.gerardomascayano.drinksapp.domain.model

import cl.gerardomascayano.drinksapp.core.diffutil.DiffUtilHelperModel

data class DrinkSearch(
    val id: Int,
    val name: String,
    val imageUrl: String?
) : DiffUtilHelperModel {

    override fun getIdentifier(): Int {
        return id
    }

}