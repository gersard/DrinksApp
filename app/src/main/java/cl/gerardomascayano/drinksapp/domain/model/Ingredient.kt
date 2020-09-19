package cl.gerardomascayano.drinksapp.domain.model

data class Ingredient(
    val id: Int,
    val name: String,
    val hasAlcohol: Boolean,
    val quantity: Float,
    val unit: Unit
)