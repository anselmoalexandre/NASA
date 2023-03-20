package mz.co.bilheteira.domain.repository

import kotlinx.coroutines.flow.Flow
import mz.co.bilheteira.api.domain.local.ApiResponse
import mz.co.bilheteira.storage.entities.CameraEntity
import mz.co.bilheteira.storage.entities.PhotoEntity
import mz.co.bilheteira.storage.entities.RoverEntity
import mz.co.bilheteira.storage.entities.relations.PhotoWithRover
import retrofit2.Response

interface NasaRepository {
    fun getAllRoversPhotos(
        earthDate: String = "2015-06-03",
        sol: Int = 10,
        privateKey: String
    ): Response<ApiResponse>

    fun getPhotos(): Flow<PhotoWithRover>

    fun getCameras(roverId: Int): Flow<CameraEntity>

    suspend fun insertRover(roverEntity: RoverEntity)

    suspend fun insertPhoto(photoEntity: PhotoEntity)

    suspend fun insertCamera(cameraEntity: CameraEntity)
}