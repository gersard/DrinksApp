package cl.gerardomascayano.drinksapp.data.db.entities.ingredient

import androidx.room.Embedded
import androidx.room.Relation
import cl.gerardomascayano.drinksapp.data.db.entities.unit.UnitEntity


data class IngredientDetailRelation(
    @Embedded val ingredientDetailEntity: IngredientDetailEntity,
    @Relation(
        parentColumn = "ingredient_id",
        entityColumn = "ingredientId"
    )
    val ingredientEntity: IngredientEntity,
    @Relation(
        parentColumn = "unit_owner_id",
        entityColumn = "unitId"
    )
    val unit: UnitEntity

)