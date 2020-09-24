package cl.gerardomascayano.drinksapp.data.db.entities.drink

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface DrinkDao {

    @Insert(onConflict = REPLACE)
    suspend fun addDrink(drink: DrinkEntity): Long

    @Query("SELECT drinkId as id, name, image_url FROM DRINK WHERE name LIKE '%' || :name || '%'")
    suspend fun getDrinksByName(name: String): List<DrinkSearchTuple>

    @Transaction
    @Query("SELECT * FROM DRINK WHERE favorite = 1")
    suspend fun getDrinkWithIngredientsFavorite(): List<DrinkWithIngredients>

    @Transaction
    @Query("SELECT * FROM DRINK WHERE favorite = 0")
    suspend fun getDrinkWithIngredientsUnfavorite(): List<DrinkWithIngredients>


}