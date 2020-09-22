package cl.gerardomascayano.drinksapp.data.repository

import cl.gerardomascayano.drinksapp.domain.model.Drink
import cl.gerardomascayano.drinksapp.domain.model.DrinkSearch

interface DrinkRepository {

    suspend fun getAllFavoriteDrink(): List<Drink>
    suspend fun getAllUnFavoriteDrink(): List<Drink>
    suspend fun getAllDrinksByName(name: String): List<DrinkSearch>
}