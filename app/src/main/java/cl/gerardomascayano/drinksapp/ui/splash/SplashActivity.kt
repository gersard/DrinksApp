package cl.gerardomascayano.drinksapp.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import cl.gerardomascayano.drinksapp.data.db.DrinksAppDatabase
import cl.gerardomascayano.drinksapp.data.db.entities.drink.DrinkEntity
import cl.gerardomascayano.drinksapp.data.db.entities.ingredient.IngredientDetailEntity
import cl.gerardomascayano.drinksapp.data.db.entities.ingredient.IngredientEntity
import cl.gerardomascayano.drinksapp.data.db.entities.unit.UnitEntity
import cl.gerardomascayano.drinksapp.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import timber.log.Timber
import java.io.IOException
import java.io.InputStream
import java.nio.charset.StandardCharsets

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        insertInitialData()
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

    private fun insertInitialData() = lifecycleScope.launch {

        val drinkDao = DrinksAppDatabase.getInstance(this@SplashActivity).drinksDao()
        val ingredientDao = DrinksAppDatabase.getInstance(this@SplashActivity).ingredientsDao()
        val stepDao = DrinksAppDatabase.getInstance(this@SplashActivity).stepDao()
        val unitDao = DrinksAppDatabase.getInstance(this@SplashActivity).unitDao()

        // UNITS
        val unitsJson = JSONArray(loadJsonFromAsset("units.json"))
        for (i in 0 until unitsJson.length()) {
            val jsonUnit = unitsJson.optJSONObject(i)
            unitDao.addUnit(UnitEntity(jsonUnit.optInt("id"), jsonUnit.optString("name"), jsonUnit.optString("abbreviation")))
        }

        // DRINKS
        val drinksJson = JSONArray(loadJsonFromAsset("drinks.json"))
        for (i in 0 until drinksJson.length()) {
            val jsonDrink = drinksJson.optJSONObject(i)
            val drinkEntity = DrinkEntity(
                jsonDrink.optInt("id"),
                jsonDrink.optString("name"),
                jsonDrink.optString("image_url"),
                jsonDrink.optDouble("rating").toFloat(),
                jsonDrink.optBoolean("favorite")
            )
            drinkDao.addDrink(drinkEntity)
            Timber.d("Se insertó: ${drinkEntity.name}")
        }

        // INGREDIENTS
        val ingredients = JSONArray(loadJsonFromAsset("ingredients.json"))
        for (i in 0 until ingredients.length()) {
            val jsonIngredient = ingredients.optJSONObject(i)
            val ingredient = IngredientEntity(jsonIngredient.optInt("id"), jsonIngredient.optString("name"), jsonIngredient.optBoolean("has_alcohol"))
            ingredientDao.addIngredient(ingredient)
        }

        // INGREDIENTS - DETAIL
        val ingredientsDetail = JSONArray(loadJsonFromAsset("ingredients_detail.json"))
        for (i in 0 until ingredientsDetail.length()) {
            val jsonDetail = ingredientsDetail.optJSONObject(i)
            val detail = IngredientDetailEntity(
                jsonDetail.optInt("id"),
                jsonDetail.optDouble("quantity").toFloat(),
                jsonDetail.optInt("ingredient_id"),
                jsonDetail.optInt("drink_id"),
                jsonDetail.optInt("unit_id")
            )
            ingredientDao.addDetailIngredient(detail)
        }

        withContext(Dispatchers.Main){
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

    private fun loadJsonFromAsset(jsonFileName: String): String? {
        return try {
            val inputStream: InputStream = assets.open(jsonFileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
    }
}