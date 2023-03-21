package mz.co.bilheteira.domain.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import mz.co.bilheteira.api.domain.local.ApiResponse
import mz.co.bilheteira.api.domain.remote.NasaApiService
import mz.co.bilheteira.domain.data.CameraModel
import mz.co.bilheteira.domain.data.PhotoModel
import mz.co.bilheteira.domain.data.RoverModel
import mz.co.bilheteira.storage.dao.NasaDao
import mz.co.bilheteira.storage.entities.CameraEntity
import mz.co.bilheteira.storage.entities.PhotoEntity
import mz.co.bilheteira.storage.entities.RoverEntity
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

    override fun getAllLocalRoversPhotos(): Flow<PhotoModel> {
        val rovers = nasaDao.getPhotos().map {
            it.roverEntity
        }.map {
            RoverModel(
                id = it.id,
                name = it.name,
                status = it.status,
                landingDate = it.landingDate,
                launchDate = it.launchDate
            )
        }

        val cameras = rovers.flatMapConcat {
            nasaDao.getCameras(it.id)
        }.map {
            CameraModel(
                id = it.id,
                name = it.name,
                fullName = it.fullName,
                roverId = it.roverId
            )
        }

        val photos = nasaDao.getPhotos().map {
            it.photoEntity
        }.map {
            PhotoModel(
                id = it.id,
                sol = it.sol,
                earthDate = it.earthDate,
                photo = it.photo,
                rover = rovers,
                camera = cameras
            )
        }

        return photos
    }

    override suspend fun insertRover(roverEntity: RoverEntity) {
        withContext(Dispatchers.IO) { nasaDao.insertRover(roverEntity) }
    }

    override suspend fun insertPhoto(photoEntity: PhotoEntity) {
        withContext(Dispatchers.IO) { nasaDao.insertPhoto(photoEntity) }
    }

    override suspend fun insertCamera(cameraEntity: CameraEntity) {
        withContext(Dispatchers.IO) { nasaDao.insertCamera(cameraEntity) }
    }
}