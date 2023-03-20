package mz.co.bilheteira.storage.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import mz.co.bilheteira.storage.entities.PhotoEntity
import mz.co.bilheteira.storage.entities.RoverEntity

internal data class PhotoWithRover(
    @Embedded val roverEntity: RoverEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "rover_id"
    )
    val photoEntity: PhotoEntity
)
