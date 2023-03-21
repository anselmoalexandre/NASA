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
import mz.co.bilheteira.storage.dao.NasaDao
import mz.co.bilheteira.storage.entity.PhotoEntity
import retrofit2.Response

internal class NasaRepositoryImpl(
    private val nasaDao: NasaDao,
    private val nasaApiService: NasaApiService
) : NasaRepository {
    override suspend fun getAllRemoteRoversPhotos(
        earthDate: String,
        sol: Int,
        privateKey: String
    ): Response<ApiResponse> {
        return withContext(Dispatchers.IO) {
            nasaApiService.getAllRoversPhotos(earthDate, sol, privateKey)
        }
    }

    override fun getLocalRoversPhotos(): Flow<PhotoModel> = nasaDao.getPhotos().map {
        val camera = CameraModel(
            id = it.camera.id,
            name = it.camera.name,
            fullName = it.camera.fullName,
            roverId = it.camera.roverId
        )

        val rover = RoverModel(
            id = it.rover.id,
            name = it.rover.name,
            status = it.rover.status,
            landingDate = it.rover.landingDate,
            launchDate = it.rover.launchDate
        )

        PhotoModel(
            id = it.id,
            sol = it.sol,
            photo = it.photo,
            earthDate = it.earthDate,
            camera = camera,
            rover = rover
        )
    }

    override suspend fun insertPhoto(photoEntity: PhotoEntity) {
        withContext(Dispatchers.IO) { nasaDao.insertPhoto(photoEntity) }
    }
}
