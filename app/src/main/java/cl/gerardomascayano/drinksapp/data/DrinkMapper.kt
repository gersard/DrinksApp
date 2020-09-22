package cl.gerardomascayano.drinksapp.data

import cl.gerardomascayano.drinksapp.domain.model.Drink
import cl.gerardomascayano.drinksapp.domain.model.Ingredient
import cl.gerardomascayano.drinksapp.domain.model.Unit
import cl.gerardomascayano.drinksapp.data.db.entities.drink.DrinkWithIngredients

class DrinkMapper() {

    fun maptoDrinkDomain(drinksWithIngredients: List<DrinkWithIngredients>): List<Drink> {
        return drinksWithIngredients.map { drinkWithIngredients ->
            val ingredients = drinkWithIngredients.ingredientsDetailRelation.map { ingredientDetailRelation ->
                val ingredientEntity = ingredientDetailRelation.ingredientEntity
                val ingredientDetailEntity = ingredientDetailRelation.ingredientDetailEntity
                val unitEntity = ingredientDetailRelation.unit
                val unit = Unit(unitEntity.abbreviation)
                Ingredient(
                    ingredientEntity.ingredientId,
                    ingredientEntity.name,
                    ingredientEntity.hasAlcohol,
                    ingredientDetailEntity.quantity,
                    unit
                )
            }

            val steps = drinkWithIngredients.steps.map { stepEntity -> stepEntity.description }

            val drinkEntity = drinkWithIngredients.drink

            Drink(
                drinkEntity.drinkId,
                drinkEntity.name,
                drinkEntity.imageUrl,
                drinkEntity.rating ?: 0f,
                drinkEntity.favorite,
                ingredients,
                steps
            )
        }
    }

}