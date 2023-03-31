package mz.co.bilheteira.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mz.co.bilheteira.api.domain.local.ApiResponse
import mz.co.bilheteira.domain.data.PhotoModel
import retrofit2.Response

class FakeNasaRepository(
    private val nasaFakeService: FakeNasaApiService,
    private val nasaFakeNasaDao: FakeNasaDao
) : NasaRepository {
    override suspend fun getAllRemoteRoversPhotos(
        roverName: String,
        earthDate: String,
        sol: Int,
        privateKey: String
    ): Response<ApiResponse> {
        return nasaFakeService.getAllRoversPhotos(roverName, earthDate, sol, privateKey)
    }

    override fun getLocalRoversPhotos(): Flow<List<PhotoModel>> {
        return nasaFakeNasaDao.getPhotos().map {
            it.map { photoEntity ->
                val camera = mz.co.bilheteira.domain.data.CameraModel(
                    id = photoEntity.camera.id,
                    name = photoEntity.camera.name,
                    fullName = photoEntity.camera.fullName,
                    roverId = photoEntity.camera.roverId
                )

                val rover = mz.co.bilheteira.domain.data.RoverModel(
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
        TODO(reason = "Out of scope")
    }

}