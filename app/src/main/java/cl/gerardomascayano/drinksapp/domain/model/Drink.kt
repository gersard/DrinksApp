package cl.gerardomascayano.drinksapp.domain.model

data class Drink(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    var rating: Float,
    var favorite: Boolean,
    val ingredients: List<Ingredient>,
    val steps: List<String>
)