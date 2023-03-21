package mz.co.bilheteira.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import mz.co.bilheteira.storage.data.CameraEntity
import mz.co.bilheteira.storage.data.RoverEntity

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val sol: Int,
    @ColumnInfo(name = "img_src")
    val photo: String,
    @ColumnInfo(name = "earth_date")
    val earthDate: String,
    val camera: CameraEntity,
    val rover: RoverEntity
)
