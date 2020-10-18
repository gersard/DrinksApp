package cl.gerardomascayano.drinksapp.data

import cl.gerardomascayano.drinksapp.data.db.entities.drink.DrinkDao
import cl.gerardomascayano.drinksapp.data.db.entities.drink.DrinkEntity
import cl.gerardomascayano.drinksapp.data.db.entities.drink.DrinkSearchTuple
import cl.gerardomascayano.drinksapp.data.db.entities.drink.DrinkWithIngredients
import javax.inject.Inject

class LocalDrinkDataSourceImpl @Inject constructor(private val drinksDao: DrinkDao) :
    DrinkDataSource {

    override suspend fun getAllFavoriteDrinks(): List<DrinkWithIngredients> = drinksDao.getDrinkWithIngredientsFavorite()
    override suspend fun getDrinkById(drinkId: Int): DrinkWithIngredients = drinksDao.getDrinkById(drinkId)
    override suspend fun getDrinksByName(name: String): List<DrinkSearchTuple> = drinksDao.getDrinksByName(name)
    override suspend fun updateDrink(drinkEntity: DrinkEntity): Int = drinksDao.updateDrink(drinkEntity)

    override suspend fun getAllUnfavoriteDrinks(): List<DrinkWithIngredients> = drinksDao.getDrinkWithIngredientsUnfavorite()
}