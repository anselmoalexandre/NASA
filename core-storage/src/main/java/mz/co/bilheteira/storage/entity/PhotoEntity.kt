package mz.co.bilheteira.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import mz.co.bilheteira.storage.data.CameraEntity
import mz.co.bilheteira.storage.data.RoverEntity
import mz.co.bilheteira.storage.data.RoverStatus

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val sol: Int,

    val camera: CameraEntity,
    @ColumnInfo(name = "img_src")
    val photo: String,
    @ColumnInfo(name = "earth_date")
    val earthDate: String,
    val rover: RoverEntity
)
