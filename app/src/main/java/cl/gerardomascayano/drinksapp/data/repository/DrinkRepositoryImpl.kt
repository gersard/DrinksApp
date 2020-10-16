package cl.gerardomascayano.drinksapp.data.repository

import cl.gerardomascayano.drinksapp.domain.model.Drink
import cl.gerardomascayano.drinksapp.data.DrinkDataSource
import cl.gerardomascayano.drinksapp.data.DrinkMapper
import cl.gerardomascayano.drinksapp.data.db.entities.drink.DrinkEntity
import cl.gerardomascayano.drinksapp.domain.model.DrinkSearch
import javax.inject.Inject

class DrinkRepositoryImpl @Inject constructor(private val dataSource: DrinkDataSource, private val mapper: DrinkMapper) : DrinkRepository {

    override suspend fun getAllFavoriteDrink(): List<Drink> {
        val favoriteDrinksDataSource = dataSource.getAllFavoriteDrinks()
        // Map to Drink domain
        return mapper.mapToDrinks(favoriteDrinksDataSource)
    }

    override suspend fun getAllUnFavoriteDrink(): List<Drink> {
        val unFavoriteDrinksDataSource = dataSource.getAllUnfavoriteDrinks()
        return mapper.mapToDrinks(unFavoriteDrinksDataSource)
    }

    override suspend fun getAllDrinksByName(name: String): List<DrinkSearch> {
        return dataSource.getDrinksByName(name).map { DrinkSearch(it.id, it.name, it.imageUrl) }
    }

    override suspend fun getDrinkById(drinkId: Int): Drink {
        val drinkEntity = dataSource.getDrinkById(drinkId)
        return mapper.mapToDrink(drinkEntity)
    }

    override suspend fun updateDrink(drink: Drink): Long {
        return dataSource.updateDrink(
            mapper.mapToDrinkEntity(drink)
        )
    }
}