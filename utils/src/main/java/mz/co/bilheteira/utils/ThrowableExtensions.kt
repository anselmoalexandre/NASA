package mz.co.bilheteira.utils

import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.handleThrowable(): Any {
    Timber.e(this)
    return when (this) {
        is SocketTimeoutException -> R.string.connection_error
        is UnknownHostException -> R.string.connection_error
        is ConnectException -> R.string.connectivity_error
        else -> this.message.toString()
    }
}
