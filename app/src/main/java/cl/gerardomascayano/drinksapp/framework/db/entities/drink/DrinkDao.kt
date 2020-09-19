package cl.gerardomascayano.drinksapp.framework.db.entities.drink

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface DrinkDao {

    @Insert(onConflict = REPLACE)
    suspend fun addDrink(drink: DrinkEntity): Long

    @Query("SELECT * FROM DRINK")
    suspend fun getDrinks(): List<DrinkEntity>

    @Transaction
    @Query("SELECT * FROM DRINK WHERE favorite = 1")
    suspend fun getDrinkWithIngredientsFavorite(): List<DrinkWithIngredients>

    @Transaction
    @Query("SELECT * FROM DRINK WHERE favorite = 0")
    suspend fun getDrinkWithIngredientsUnfavorite(): List<DrinkWithIngredients>


}