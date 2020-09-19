package cl.gerardomascayano.drinksapp.framework.repository

import cl.gerardomascayano.drinksapp.domain.model.Drink

interface DrinkRepository {

    suspend fun getAllFavoriteDrink(): List<Drink>
    suspend fun getAllUnFavoriteDrink(): List<Drink>
}