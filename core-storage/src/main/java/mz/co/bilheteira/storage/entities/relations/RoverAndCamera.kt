package mz.co.bilheteira.storage.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import mz.co.bilheteira.storage.entities.CameraEntity
import mz.co.bilheteira.storage.entities.RoverEntity

data class RoverAndCamera(
    @Embedded val roverEntity: RoverEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "rover_id"
    )
    val cameraEntity: CameraEntity
)
