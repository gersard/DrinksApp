package cl.gerardomascayano.drinksapp.domain

import cl.gerardomascayano.drinksapp.domain.model.Drink
import cl.gerardomascayano.drinksapp.framework.repository.DrinkRepository
import javax.inject.Inject


class DrinkUseCaseImpl @Inject constructor(private val repo: DrinkRepository) : DrinkUseCase {

    override suspend fun getAllFavoriteDrinks(): List<Drink> {
        return repo.getAllFavoriteDrink()
    }

    override suspend fun getAllUnFavoriteDrinks(): List<Drink> {
        return repo.getAllUnFavoriteDrink()
    }
}