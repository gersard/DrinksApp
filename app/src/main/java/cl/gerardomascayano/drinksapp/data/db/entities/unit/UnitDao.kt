package cl.gerardomascayano.drinksapp.data.db.entities.unit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface UnitDao {

    @Insert(onConflict = REPLACE)
    suspend fun addAllUnit(units: List<UnitEntity>)

    @Insert(onConflict = REPLACE)
    suspend fun addUnit(unit: UnitEntity)
}