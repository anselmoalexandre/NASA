package mz.co.bilheteira.domain.repository

import kotlinx.coroutines.flow.Flow
import mz.co.bilheteira.api.domain.local.ApiResponse
import mz.co.bilheteira.domain.data.PhotoModel
import retrofit2.Response

interface NasaRepository {
    suspend fun getAllRemoteRoversPhotos(
        roverName:String,
        earthDate: String,
        sol: Int,
        privateKey: String
    ): Response<ApiResponse>

    fun getLocalRoversPhotos(): Flow<List<PhotoModel>>

    suspend fun insertPhoto(photoModel: PhotoModel)
}
