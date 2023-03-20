package mz.co.bilheteira.storage.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class PhotoEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val sol: Int,
    @ColumnInfo(name = "img_src")
    val photo: String,
    @ColumnInfo(name = "earth_date")
    val earthDate: String,
    @ColumnInfo(name = "rover_id")
    val roverId: Int
)
