package cl.gerardomascayano.drinksapp.framework.data

import cl.gerardomascayano.drinksapp.framework.db.entities.drink.DrinkWithIngredients

interface DrinkDataSource {

    suspend fun getAllUnfavoriteDrinks(): List<DrinkWithIngredients>

    suspend fun getAllFavoriteDrinks(): List<DrinkWithIngredients>

}