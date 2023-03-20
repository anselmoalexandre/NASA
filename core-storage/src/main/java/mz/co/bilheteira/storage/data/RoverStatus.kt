package mz.co.bilheteira.storage.data

import androidx.annotation.Keep

@Keep
internal enum class RoverStatus(status:String) {
    ACTIVE(status = "active"),
    DEACTIVATED(status = "deactivated")
}