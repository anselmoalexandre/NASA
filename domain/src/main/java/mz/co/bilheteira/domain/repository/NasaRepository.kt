package mz.co.bilheteira.domain.repository

import kotlinx.coroutines.flow.Flow
import mz.co.bilheteira.api.domain.local.ApiResponse
import mz.co.bilheteira.domain.data.PhotoModel
import mz.co.bilheteira.storage.entities.CameraEntity
import mz.co.bilheteira.storage.entities.PhotoEntity
import mz.co.bilheteira.storage.entities.RoverEntity
import mz.co.bilheteira.storage.entities.relations.PhotoWithRover
import retrofit2.Response

interface NasaRepository {
    suspend fun getAllRemoteRoversPhotos(
        earthDate: String,
        sol: Int,
        privateKey: String
    ): Response<ApiResponse>

    fun getAllLocalRoversPhotos(): Flow<PhotoModel>

    suspend fun insertRover(roverEntity: RoverEntity)

    suspend fun insertPhoto(photoEntity: PhotoEntity)

    suspend fun insertCamera(cameraEntity: CameraEntity)
}