package cl.gerardomascayano.drinksapp.data

import cl.gerardomascayano.drinksapp.data.db.entities.drink.DrinkSearchTuple
import cl.gerardomascayano.drinksapp.data.db.entities.drink.DrinkWithIngredients

interface DrinkDataSource {

    suspend fun getAllUnfavoriteDrinks(): List<DrinkWithIngredients>

    suspend fun getAllFavoriteDrinks(): List<DrinkWithIngredients>

    suspend fun getDrinksByName(name: String): List<DrinkSearchTuple>

}