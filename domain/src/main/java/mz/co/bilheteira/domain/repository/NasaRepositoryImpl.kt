package mz.co.bilheteira.domain.repository

import kotlinx.coroutines.flow.Flow
import mz.co.bilheteira.api.domain.local.ApiResponse
import mz.co.bilheteira.api.domain.remote.PhotosApiService
import mz.co.bilheteira.storage.dao.NasaDao
import mz.co.bilheteira.storage.entities.CameraEntity
import mz.co.bilheteira.storage.entities.PhotoEntity
import mz.co.bilheteira.storage.entities.RoverEntity
import mz.co.bilheteira.storage.entities.relations.PhotoWithRover
import retrofit2.Response

internal class NasaRepositoryImpl(
    private val nasaDao: NasaDao,
    private val photosApiService: PhotosApiService
) : NasaRepository {
    override fun getAllRoversPhotos(
        earthDate: String,
        sol: Int,
        privateKey: String
    ): Response<ApiResponse> {
        TODO("Not yet implemented")
    }

    override fun getPhotos(): Flow<PhotoWithRover> {
        TODO("Not yet implemented")
    }

    override fun getCameras(roverId: Int): Flow<CameraEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insertRover(roverEntity: RoverEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertPhoto(photoEntity: PhotoEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertCamera(cameraEntity: CameraEntity) {
        TODO("Not yet implemented")
    }
}