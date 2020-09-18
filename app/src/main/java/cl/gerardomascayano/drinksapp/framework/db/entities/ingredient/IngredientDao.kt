package cl.gerardomascayano.drinksapp.framework.db.entities.ingredient

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface IngredientDao {

    @Insert(onConflict = REPLACE)
    suspend fun addIngredient(ingredient: IngredientEntity)

    @Insert(onConflict = REPLACE)
    suspend fun addAllIngredients(ingredients: List<IngredientEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun addAllDetailIngredients(detailIngredients: List<IngredientDetailEntity>)

    @Query("SELECT * FROM ingredient")
    suspend fun getIngredients(): List<IngredientEntity>

//    @Transaction
//    @Query("SELECT * FROM ingredient")
//    suspend fun getDrinkWithIngredients(): List<DrinkWithIngredients>


}