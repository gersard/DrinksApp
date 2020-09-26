package cl.gerardomascayano.drinksapp.domain.list

import cl.gerardomascayano.drinksapp.domain.model.Drink
import cl.gerardomascayano.drinksapp.domain.model.DrinkSearch

interface DrinkUseCase {

    suspend fun getAllFavoriteDrinks(): List<Drink>
    suspend fun getAllUnFavoriteDrinks(): List<Drink>
    suspend fun getDrinksByName(name: String): List<DrinkSearch>
}