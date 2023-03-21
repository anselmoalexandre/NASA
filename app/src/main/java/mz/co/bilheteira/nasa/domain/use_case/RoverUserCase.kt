package mz.co.bilheteira.nasa.domain.use_case

import kotlinx.coroutines.flow.Flow
import mz.co.bilheteira.api.domain.local.ApiResponse
import mz.co.bilheteira.domain.data.PhotoModel
import mz.co.bilheteira.domain.data.toPhotoEntity
import mz.co.bilheteira.domain.repository.NasaRepository
import retrofit2.Response

class RoverUserCase(private val nasaRepository: NasaRepository) {

    suspend fun getAllRemoteRoversPhotos(
        earthDate: String = "2012-08-16",
        sol: Int = 10,
        privateKey: String
    ): Response<ApiResponse> = nasaRepository.getAllRemoteRoversPhotos(earthDate, sol, privateKey)

    fun getLocalRoversPhotos(): Flow<PhotoModel> = nasaRepository.getLocalRoversPhotos()

    suspend fun insertPhoto(photoEntity: PhotoModel) {
        nasaRepository.insertPhoto(photoEntity = photoEntity.toPhotoEntity())
    }
}
