package mz.co.bilheteira.domain.data

import androidx.annotation.Keep
import mz.co.bilheteira.api.domain.local.RemotePhotos
import mz.co.bilheteira.storage.data.CameraEntity
import mz.co.bilheteira.storage.data.RoverEntity
import mz.co.bilheteira.storage.data.RoverStatus
import mz.co.bilheteira.storage.entity.PhotoEntity

@Keep
data class PhotoModel(
    val id: Int,
    val sol: Int,
    val photo: String,
    val earthDate: String,
    val camera: CameraModel,
    val rover: RoverModel
)

@Keep
data class RoverModel(
    val id: Int,
    val name: String,
    val status: RoverStatus,
    val landingDate: String,
    val launchDate: String
)

@Keep
data class CameraModel(
    val id: Int,
    val name: String,
    val fullName: String,
    val roverId: Int
)

fun PhotoModel.toPhotoEntity() = PhotoEntity(
    id = id,
    sol = sol,
    photo = photo,
    earthDate = earthDate,
    camera = camera.toCameraEntity(),
    rover = rover.toRoverEntity()
)

fun RemotePhotos.toPhotoModel() = PhotoModel(
    id = id,
    sol = sol,
    photo = img_src,
    earthDate = earth_date,
    camera = CameraModel(
        id = camera.id,
        name = camera.name,
        fullName = camera.full_name,
        roverId = camera.rover_id
    ),
    rover = RoverModel(
        id = rover.id,
        name = rover.name,
        status = when (rover.status) {
            RoverStatus.ACTIVE.status -> RoverStatus.ACTIVE
            else -> RoverStatus.DEACTIVATED
        },
        landingDate = rover.landing_date,
        launchDate = rover.launch_date
    )
)

fun CameraModel.toCameraEntity() = CameraEntity(id, name, fullName, roverId)

fun RoverModel.toRoverEntity() = RoverEntity(id, name, status, landingDate, launchDate)