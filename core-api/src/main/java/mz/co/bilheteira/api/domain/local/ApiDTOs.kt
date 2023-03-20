package mz.co.bilheteira.api.domain.local

import androidx.annotation.Keep

@Keep
data class ApiResponse(
    val photos: List<RemotePhotos>
)

@Keep
data class RemotePhotos(
    val id: Int,
    val sol: Int,
    val img_src: String,
    val earth_date: String,
    val camera: RemoteCamera,
    val rover: RemoteRover
)

@Keep
data class RemoteCamera(
    val id: Int,
    val name: String,
    val rover_id: Int,
    val full_name: String
)

@Keep
data class RemoteRover(
    val id: Int,
    val name: String,
    val landing_date: String,
    val launch_date: String,
    val status: String
)
