package mz.co.bilheteira.domain.repository

import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mz.co.bilheteira.api.domain.local.ApiResponse
import mz.co.bilheteira.api.domain.remote.NasaApiService
import mz.co.bilheteira.domain.data.CameraModel
import mz.co.bilheteira.domain.data.PhotoModel
import mz.co.bilheteira.domain.data.RoverModel
import mz.co.bilheteira.storage.dao.NasaDao

import retrofit2.Response

class FakeNasaRepository : NasaRepository {
    @MockK
    private lateinit var nasaApiService: NasaApiService

    @MockK
    private lateinit var nasaDao: NasaDao


    override suspend fun getAllRemoteRoversPhotos(
        roverName: String,
        earthDate: String,
        sol: Int,
        privateKey: String
    ): Response<ApiResponse> {
        return nasaApiService.getAllRoversPhotos(
            "Spirit",
            "2012-06-09",
            10,
            "ajkshasbabsajabn"
        )
    }

    override fun getLocalRoversPhotos(): Flow<List<PhotoModel>> {
        return nasaDao.getPhotos().map {
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
    }

    override suspend fun insertPhoto(photoModel: PhotoModel) {
        TODO("Not yet implemented")
    }
}