package cl.gerardomascayano.drinksapp.framework.db.entities.drink

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import cl.gerardomascayano.drinksapp.framework.db.entities.ingredient.IngredientDetailEntity
import cl.gerardomascayano.drinksapp.framework.db.entities.ingredient.IngredientDetailRelation
import cl.gerardomascayano.drinksapp.framework.db.entities.ingredient.IngredientEntity
import cl.gerardomascayano.drinksapp.framework.db.entities.step.StepEntity

data class DrinkWithIngredients(
    @Embedded val drink: DrinkEntity,
    @Relation(
        parentColumn = "drinkId",
        entityColumn = "drink_owner_id",
        entity = IngredientDetailEntity::class
    )
    val ingredientsDetailRelation: List<IngredientDetailRelation>,
    @Relation(
        parentColumn = "drinkId",
        entityColumn = "drink_owner_id"
    )
    val steps: List<StepEntity>
)