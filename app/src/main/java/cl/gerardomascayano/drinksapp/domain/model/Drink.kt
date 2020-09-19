package cl.gerardomascayano.drinksapp.domain.model

data class Drink(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val rating: Float,
    val favorite: Boolean,
    val ingredients: List<Ingredient>,
    val steps: List<String>
)