package mz.co.bilheteira.domain.repository

import kotlinx.coroutines.flow.Flow
import mz.co.bilheteira.api.domain.local.ApiResponse
import mz.co.bilheteira.domain.data.PhotoModel
import mz.co.bilheteira.storage.entity.PhotoEntity
import retrofit2.Response

interface NasaRepository {
    suspend fun getAllRemoteRoversPhotos(
        earthDate: String,
        sol: Int,
        privateKey: String
    ): Response<ApiResponse>

    fun getLocalRoversPhotos(): Flow<PhotoModel>

    suspend fun insertPhoto(photoEntity: PhotoEntity)
}
