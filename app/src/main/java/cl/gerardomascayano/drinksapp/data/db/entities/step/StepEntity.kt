package cl.gerardomascayano.drinksapp.data.db.entities.step

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//TODO add time field to complete the step
@Entity(tableName = "step")
data class StepEntity(
    @PrimaryKey(autoGenerate = true) val stepId: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "drink_owner_id") val drinkOwnerId: Int
)