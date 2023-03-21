package mz.co.bilheteira.domain.data

import androidx.annotation.Keep
import kotlinx.coroutines.flow.Flow
import mz.co.bilheteira.storage.data.RoverStatus

@Keep
data class PhotoModel(
    val id: Int,
    val sol: Int,
    val photo: String,
    val earthDate: String,
    val rover: Flow<RoverModel>,
    val camera: Flow<CameraModel>
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
