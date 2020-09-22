package cl.gerardomascayano.drinksapp.data

import cl.gerardomascayano.drinksapp.data.db.entities.drink.DrinkDao
import cl.gerardomascayano.drinksapp.data.db.entities.drink.DrinkSearchTuple
import cl.gerardomascayano.drinksapp.data.db.entities.drink.DrinkWithIngredients
import javax.inject.Inject

class LocalDrinkDataSourceImpl @Inject constructor(private val drinksDao: DrinkDao) :
    DrinkDataSource {

    override suspend fun getAllFavoriteDrinks(): List<DrinkWithIngredients> = drinksDao.getDrinkWithIngredientsFavorite()
    override suspend fun getDrinksByName(name: String): List<DrinkSearchTuple> = drinksDao.getDrinksByName(name)
    override suspend fun getAllUnfavoriteDrinks(): List<DrinkWithIngredients> = drinksDao.getDrinkWithIngredientsUnfavorite()
}