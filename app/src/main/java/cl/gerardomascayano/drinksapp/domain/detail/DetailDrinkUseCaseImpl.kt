package cl.gerardomascayano.drinksapp.domain.detail

import cl.gerardomascayano.drinksapp.data.repository.DrinkRepository
import cl.gerardomascayano.drinksapp.domain.model.Drink
import javax.inject.Inject

class DetailDrinkUseCaseImpl @Inject constructor(private val repo: DrinkRepository) : DetailDrinkUseCase {

    override suspend fun getDrink(drinkId: Int): Drink {
        return repo.getDrinkById(drinkId)
    }

    override suspend fun updateDrink(drink: Drink): Boolean {
        val idDrinkUpdated = repo.updateDrink(drink)
        return idDrinkUpdated.toInt() == drink.id
    }
}