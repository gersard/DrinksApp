package cl.gerardomascayano.drinksapp.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.gerardomascayano.drinksapp.framework.db.entities.drink.DrinkDao
import cl.gerardomascayano.drinksapp.framework.db.entities.drink.DrinkEntity
import cl.gerardomascayano.drinksapp.framework.db.entities.ingredient.IngredientDao
import cl.gerardomascayano.drinksapp.framework.db.entities.ingredient.IngredientDetailEntity
import cl.gerardomascayano.drinksapp.framework.db.entities.ingredient.IngredientEntity
import cl.gerardomascayano.drinksapp.framework.db.entities.step.StepDao
import cl.gerardomascayano.drinksapp.framework.db.entities.step.StepEntity
import cl.gerardomascayano.drinksapp.framework.db.entities.unit.UnitDao
import cl.gerardomascayano.drinksapp.framework.db.entities.unit.UnitEntity

@Database(
    entities = [DrinkEntity::class, IngredientEntity::class, StepEntity::class, UnitEntity::class, IngredientDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DrinksAppDatabase : RoomDatabase() {

    abstract fun drinksDao(): DrinkDao
    abstract fun ingredientsDao(): IngredientDao
    abstract fun stepDao(): StepDao
    abstract fun unitDao(): UnitDao

    companion object {
        const val DATABASE_NAME = "drinks_app"
        private var instance: DrinksAppDatabase? = null

        private fun create(context: Context): DrinksAppDatabase =
            Room.databaseBuilder(context, DrinksAppDatabase::class.java, "drinks_app")
                .build()

        fun getInstance(context: Context) = (instance ?: create(context)).also { instance = it }
    }
}