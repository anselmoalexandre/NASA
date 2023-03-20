package mz.co.bilheteira.api.domain.remote

import mz.co.bilheteira.api.domain.local.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface PhotosApiService {
    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    fun getAllRoversPhotos(
        @Query("earth_date") earthDate: String = "2015-06-03",
        @Query("sol") sol: Int = 10,
        @Query("api_key") privateKey: String
    ): Response<ApiResponse>
}
