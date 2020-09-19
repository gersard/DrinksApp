package cl.gerardomascayano.drinksapp.framework.db.entities.drink

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drink")
data class DrinkEntity(
    // 0 for autogenerate
    @PrimaryKey(autoGenerate = true) val drinkId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image_url") val imageUrl: String?,
//    @ColumnInfo (name ="ingredients") val name: String = "",
//    @ColumnInfo (name ="steps") val name: String = "",
    @ColumnInfo(name = "rating") val rating: Float?,
    @ColumnInfo(name = "favorite") val favorite: Boolean
){


}