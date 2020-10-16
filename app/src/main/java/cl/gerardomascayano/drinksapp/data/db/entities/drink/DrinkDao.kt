package cl.gerardomascayano.drinksapp.data.db.entities.drink

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface DrinkDao {

    @Insert(onConflict = REPLACE)
    suspend fun addDrink(drink: DrinkEntity): Long

    @Query("SELECT drinkId as id, name, image_url FROM DRINK WHERE name LIKE '%' || :name || '%'")
    suspend fun getDrinksByName(name: String): List<DrinkSearchTuple>

    @Query("SELECT * FROM DRINK WHERE drinkId = :id")
    suspend fun getDrinkById(id: Int): DrinkWithIngredients

    @Transaction
    @Query("SELECT * FROM DRINK WHERE favorite = 1")
    suspend fun getDrinkWithIngredientsFavorite(): List<DrinkWithIngredients>

    @Transaction
    @Query("SELECT * FROM DRINK WHERE favorite = 0")
    suspend fun getDrinkWithIngredientsUnfavorite(): List<DrinkWithIngredients>

    @Update(entity = DrinkEntity::class)
    suspend fun updateDrink(drinkEntity: DrinkEntity): Long


}