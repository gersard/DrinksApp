package cl.gerardomascayano.drinksapp.framework.repository

import cl.gerardomascayano.drinksapp.domain.model.Drink
import cl.gerardomascayano.drinksapp.framework.data.DrinkDataSource
import cl.gerardomascayano.drinksapp.framework.data.DrinkMapper
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
}