package mz.co.bilheteira.domain.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import mz.co.bilheteira.api.domain.local.ApiResponse
import mz.co.bilheteira.api.domain.remote.NasaApiService
import mz.co.bilheteira.domain.data.CameraModel
import mz.co.bilheteira.domain.data.PhotoModel
import mz.co.bilheteira.domain.data.RoverModel
import mz.co.bilheteira.domain.data.toPhotoEntity
import mz.co.bilheteira.storage.dao.NasaDao
import retrofit2.Response

internal class NasaRepositoryImpl(
    private val nasaDao: NasaDao,
    private val nasaApiService: NasaApiService
) : NasaRepository {
    override suspend fun getAllRemoteRoversPhotos(
        roverName: String,
        earthDate: String,
        sol: Int,
        privateKey: String
    ): Response<ApiResponse> {
        return withContext(Dispatchers.IO) {
            nasaApiService.getAllRoversPhotos(roverName, earthDate, sol, privateKey)
        }
    }

    override fun getLocalRoversPhotos(): Flow<List<PhotoModel>> = nasaDao.getPhotos().map {
        it.map { photoEntity ->
            val camera = CameraModel(
                id = photoEntity.camera.id,
                name = photoEntity.camera.name,
                fullName = photoEntity.camera.fullName,
                roverId = photoEntity.camera.roverId
            )

            val rover = RoverModel(
                id = photoEntity.rover.id,
                name = photoEntity.rover.name,
                status = photoEntity.rover.status,
                landingDate = photoEntity.rover.landingDate,
                launchDate = photoEntity.rover.launchDate
            )

            PhotoModel(
                id = photoEntity.id,
                sol = photoEntity.sol,
                photo = photoEntity.photo,
                earthDate = photoEntity.earthDate,
                camera = camera,
                rover = rover
            )
        }
    }

    override suspend fun insertPhoto(photoModel: PhotoModel) {
        withContext(Dispatchers.IO) { nasaDao.insertPhoto(photoModel.toPhotoEntity()) }
    }
}
