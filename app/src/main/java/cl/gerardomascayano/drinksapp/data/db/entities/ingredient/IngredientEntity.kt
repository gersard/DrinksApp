package cl.gerardomascayano.drinksapp.data.db.entities.ingredient

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient")
data class IngredientEntity(
    // 0 for autogenerate
    @PrimaryKey(autoGenerate = true) val ingredientId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "has_alcohol") val hasAlcohol: Boolean = false
)