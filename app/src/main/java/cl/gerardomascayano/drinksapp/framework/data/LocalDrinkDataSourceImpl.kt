package cl.gerardomascayano.drinksapp.framework.data

import cl.gerardomascayano.drinksapp.framework.db.entities.drink.DrinkDao
import cl.gerardomascayano.drinksapp.framework.db.entities.drink.DrinkWithIngredients
import javax.inject.Inject

class LocalDrinkDataSourceImpl @Inject constructor(private val drinksDao: DrinkDao) : DrinkDataSource {

    override suspend fun getAllUnfavoriteDrinks(): List<DrinkWithIngredients> = drinksDao.getDrinkWithIngredientsFavorite()

    override suspend fun getAllFavoriteDrinks(): List<DrinkWithIngredients> = drinksDao.getDrinkWithIngredientsUnfavorite()
}