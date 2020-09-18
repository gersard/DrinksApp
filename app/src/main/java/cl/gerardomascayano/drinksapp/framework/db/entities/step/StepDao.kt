package cl.gerardomascayano.drinksapp.framework.db.entities.step

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface StepDao {

    @Insert(onConflict = REPLACE)
    suspend fun addAllSteps(steps: List<StepEntity>)

}