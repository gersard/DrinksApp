package cl.gerardomascayano.drinksapp.data.db.entities.drink

import androidx.room.ColumnInfo

data class DrinkSearchTuple(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image_url") val imageUrl: String?
)