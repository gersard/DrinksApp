package cl.gerardomascayano.drinksapp.data.db.entities.step

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface StepDao {

    @Insert(onConflict = REPLACE)
    suspend fun addAllSteps(steps: List<StepEntity>)

}