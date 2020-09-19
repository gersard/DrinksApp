package cl.gerardomascayano.drinksapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import cl.gerardomascayano.drinksapp.R
import cl.gerardomascayano.drinksapp.framework.db.DrinksAppDatabase
import cl.gerardomascayano.drinksapp.framework.db.entities.drink.DrinkEntity
import cl.gerardomascayano.drinksapp.framework.db.entities.ingredient.IngredientDetailEntity
import cl.gerardomascayano.drinksapp.framework.db.entities.ingredient.IngredientEntity
import cl.gerardomascayano.drinksapp.framework.db.entities.step.StepEntity
import cl.gerardomascayano.drinksapp.framework.db.entities.unit.UnitEntity
import cl.gerardomascayano.drinksapp.ui.MainActivity
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        insertInitialData()

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun insertInitialData() {
        val drinkDao = DrinksAppDatabase.getInstance(this).drinksDao()
        val ingredientDao = DrinksAppDatabase.getInstance(this).ingredientsDao()
        val stepDao = DrinksAppDatabase.getInstance(this).stepDao()
        val unitDao = DrinksAppDatabase.getInstance(this).unitDao()

        lifecycleScope.launch {
            // Unidades de medida
            val units = listOf(
                UnitEntity(1, "Milimeter", "ml"),
                UnitEntity(2, "Ounce", "oz")
            )
            unitDao.addAllUnit(units)

            // Drinks
            val drink = DrinkEntity(0, "Piña Colada", "https://dam.cocinafacil.com.mx/wp-content/uploads/2019/01/pina-colada.jpg", 3.0f, true)
            val drinkId = drinkDao.addDrink(drink).toInt()

            // Ingredientes
            val ingredients = listOf(
                IngredientEntity(0, "Coconut cream", false),
                IngredientEntity(0, "Pineapple juice", false),
                IngredientEntity(0, "Ron", true)
            )
            val ingredientsIds = ingredientDao.addAllIngredients(ingredients).map { it.toInt() }

            // Ingredient Detail
            val ingredientsDetail = listOf(
                IngredientDetailEntity(0, 90f, ingredientsIds[0], drinkId, 1),
                IngredientDetailEntity(0, 90f, ingredientsIds[1], drinkId, 1),
                IngredientDetailEntity(0, 60f, ingredientsIds[2], drinkId, 1)
            )
            ingredientDao.addAllDetailIngredients(ingredientsDetail)

            val steps = listOf(
                StepEntity(0, "Poner ingredientes en licuadora", drinkId),
                StepEntity(1, "Añadir hielo a gusto", drinkId),
                StepEntity(2, "Licuar y servir en copa alta, de preferencia copa hurricane", drinkId)
            )
            stepDao.addAllSteps(steps)
        }
    }
}