package cl.gerardomascayano.drinksapp.domain.detail

import cl.gerardomascayano.drinksapp.domain.model.Drink

interface DetailDrinkUseCase {

    suspend fun getDrink(drinkId: Int): Drink

}