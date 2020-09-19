package cl.gerardomascayano.drinksapp.domain

import cl.gerardomascayano.drinksapp.domain.model.Drink

interface DrinkUseCase {

    suspend fun getAllFavoriteDrinks(): List<Drink>
    suspend fun getAllUnFavoriteDrinks(): List<Drink>

}