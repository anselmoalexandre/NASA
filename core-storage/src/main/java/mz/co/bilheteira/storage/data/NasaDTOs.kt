package mz.co.bilheteira.storage.data

import androidx.annotation.Keep

@Keep
enum class RoverStatus(val status:String) {
    ACTIVE(status = "active"),
    DEACTIVATED(status = "deactivated")
}

data class CameraEntity(
    val id: Int,
    val name: String,
    val fullName: String,
    val roverId: Int
)

data class RoverEntity(
    val id: Int,
    val name: String,
    val status: RoverStatus,
    val landingDate: String,
    val launchDate: String
)
