package mz.co.bilheteira.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "camera")
internal data class CameraEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @ColumnInfo(name = "rover_id")
    val roverId: Int
)
