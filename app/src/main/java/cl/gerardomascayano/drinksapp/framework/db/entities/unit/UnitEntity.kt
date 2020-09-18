package cl.gerardomascayano.drinksapp.framework.db.entities.unit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unit")
data class UnitEntity(
    @PrimaryKey(autoGenerate = true) val unitId: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "abbreviation") val abbreviation: String
)