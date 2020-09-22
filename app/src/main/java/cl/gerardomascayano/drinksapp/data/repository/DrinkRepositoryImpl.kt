package cl.gerardomascayano.drinksapp.data.repository

import cl.gerardomascayano.drinksapp.domain.model.Drink
import cl.gerardomascayano.drinksapp.data.DrinkDataSource
import cl.gerardomascayano.drinksapp.data.DrinkMapper
import cl.gerardomascayano.drinksapp.domain.model.DrinkSearch
import javax.inject.Inject

class DrinkRepositoryImpl @Inject constructor(private val dataSource: DrinkDataSource, private val mapper: DrinkMapper) : DrinkRepository {

    override suspend fun getAllFavoriteDrink(): List<Drink> {
        val favoriteDrinksDataSource = dataSource.getAllFavoriteDrinks()
        // Map to Drink domain
        return mapper.maptoDrinkDomain(favoriteDrinksDataSource)
    }

    override suspend fun getAllUnFavoriteDrink(): List<Drink> {
        val unFavoriteDrinksDataSource = dataSource.getAllUnfavoriteDrinks()
        return mapper.maptoDrinkDomain(unFavoriteDrinksDataSource)
    }

    override suspend fun getAllDrinksByName(name: String): List<DrinkSearch> {
        return dataSource.getDrinksByName(name).map { DrinkSearch(it.name, it.imageUrl) }
    }
}