package mz.co.bilheteira.storage.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import mz.co.bilheteira.storage.entities.CameraEntity
import mz.co.bilheteira.storage.entities.PhotoEntity

internal data class RoverAndCamera(
    @Embedded val roverEntity: PhotoEntity,
    @Relation(
        parentColumn = "rover_id",
        entityColumn = "rover_id"
    )
    val cameraEntity: CameraEntity
)
