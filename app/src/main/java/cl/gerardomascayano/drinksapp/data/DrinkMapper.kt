package cl.gerardomascayano.drinksapp.data

import cl.gerardomascayano.drinksapp.data.db.entities.drink.DrinkEntity
import cl.gerardomascayano.drinksapp.domain.model.Drink
import cl.gerardomascayano.drinksapp.domain.model.Ingredient
import cl.gerardomascayano.drinksapp.domain.model.Unit
import cl.gerardomascayano.drinksapp.data.db.entities.drink.DrinkWithIngredients

class DrinkMapper() {

    fun mapToDrink(drinkWithIngredients: DrinkWithIngredients): Drink {
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

        return Drink(
            drinkEntity.drinkId,
            drinkEntity.name,
            drinkEntity.imageUrl,
            drinkEntity.rating ?: 0f,
            drinkEntity.favorite,
            ingredients,
            steps
        )
    }

    fun mapToDrinks(drinksWithIngredients: List<DrinkWithIngredients>): List<Drink> {
        return drinksWithIngredients.map { drinkWithIngredients ->
            mapToDrink(drinkWithIngredients)
        }
    }

    fun mapToDrinkEntity(drink: Drink): DrinkEntity {
        return DrinkEntity(drink.id, drink.name, drink.imageUrl, drink.rating, drink.favorite)
    }

}