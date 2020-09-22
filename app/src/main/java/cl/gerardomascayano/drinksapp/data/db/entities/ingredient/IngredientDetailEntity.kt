package cl.gerardomascayano.drinksapp.data.db.entities.ingredient

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient_detail")
data class IngredientDetailEntity(
    @PrimaryKey(autoGenerate = true) val idIngredientDetail: Int,
    @ColumnInfo(name = "quantity") val quantity: Float,
    @ColumnInfo(name = "ingredient_id") val ingredientId: Int,
    @ColumnInfo(name = "drink_owner_id") val drinkOwnerId: Int,
    @ColumnInfo(name = "unit_owner_id") val unitOwnerId: Int
)