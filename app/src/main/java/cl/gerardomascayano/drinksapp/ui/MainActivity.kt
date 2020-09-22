package cl.gerardomascayano.drinksapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.gerardomascayano.drinksapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





    }
}


//val drinkDao = DrinksAppDatabase.getInstance(this).drinksDao()
//val ingredientDao = DrinksAppDatabase.getInstance(this).ingredientsDao()
//val stepDao = DrinksAppDatabase.getInstance(this).stepDao()
//val unitDao = DrinksAppDatabase.getInstance(this).unitDao()


//lifecycleScope.launch {
//
//    // UNIDADES DE MEDIDA
//    val units = listOf(
//        UnitEntity(1, "Milimeter", "ml"),
//        UnitEntity(2, "Ounce", "oz")
//    )
//    unitDao.addAllUnit(units)
//
//    val drink = DrinkEntity(0, "Piña Colada", "", 5.0f, true)
//    val drinkId = drinkDao.addDrink(drink)
//
//    val ingredients = listOf(
//        IngredientEntity(1, "Crema de Coco", false),
//        IngredientEntity(2, "Jugo de Piña", false),
//        IngredientEntity(3, "Ron", true)
//    )
//    ingredientDao.addAllIngredients(ingredients)
//
//    val ingredientsDetailEntity = listOf(
//        IngredientDetailEntity(1, 90f, 1, drinkId.toInt(), 1),
//        IngredientDetailEntity(2, 90f, 2, drinkId.toInt(), 1),
//        IngredientDetailEntity(3, 60f, 3, drinkId.toInt(), 1)
//    )
//    ingredientDao.addAllDetailIngredients(ingredientsDetailEntity)
//
//    val steps = listOf(
//        StepEntity(1, "Poner ingredients en licuadora", drinkId.toInt()),
//        StepEntity(2, "Añadir hielo a gusto", drinkId.toInt()),
//        StepEntity(3, "Licuar y servir en copa alta", drinkId.toInt())
//    )
//    stepDao.addAllSteps(steps)
//
//}



//
//lifecycleScope.launch {
//    val drinkWithIngredients = drinkDao.getDrinkWithIngredients()
//
//    drinkWithIngredients.forEach {
//        val drink = it.drink
//        val ingredients = it.ingredientsDetailRelation
//        val steps = it.steps
//        var logMessage = drink.name
//
//        ingredients.forEach { ingredientRelation ->
//            logMessage += "\n${ingredientRelation.ingredientEntity.name} ${ingredientRelation.ingredientDetailEntity.quantity}${ingredientRelation.unit.abbreviation}"
//        }
//
//        steps.forEach { step ->
//            logMessage += "\n${step.description}"
//        }
//        Log.d("MainActivity", logMessage)
//    }
//}