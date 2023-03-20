package mz.co.bilheteira.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import mz.co.bilheteira.storage.data.RoverStatus

@Entity(tableName = "rover")
data class RoverEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val status: RoverStatus,
    @ColumnInfo(name = "landing_date")
    val landingDate: String,
    @ColumnInfo(name = "launch_date")
    val launchDate: String
)
