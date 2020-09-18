package cl.gerardomascayano.drinksapp.framework.db.entities.unit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UnitDao {

    @Insert(onConflict = REPLACE)
    suspend fun addAllUnit(units: List<UnitEntity>)

}