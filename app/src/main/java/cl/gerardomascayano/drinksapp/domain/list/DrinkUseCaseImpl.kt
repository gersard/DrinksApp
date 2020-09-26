package cl.gerardomascayano.drinksapp.domain.list

import cl.gerardomascayano.drinksapp.domain.model.Drink
import cl.gerardomascayano.drinksapp.data.repository.DrinkRepository
import cl.gerardomascayano.drinksapp.domain.model.DrinkSearch
import javax.inject.Inject


class DrinkUseCaseImpl @Inject constructor(private val repo: DrinkRepository) :
    DrinkUseCase {

    override suspend fun getAllFavoriteDrinks(): List<Drink> {
        return repo.getAllFavoriteDrink()
    }

    override suspend fun getAllUnFavoriteDrinks(): List<Drink> {
        return repo.getAllUnFavoriteDrink()
    }

    override suspend fun getDrinksByName(name: String): List<DrinkSearch> {
        return repo.getAllDrinksByName(name)
    }
}