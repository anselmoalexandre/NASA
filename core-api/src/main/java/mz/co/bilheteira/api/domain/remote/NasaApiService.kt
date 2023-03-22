package mz.co.bilheteira.api.domain.remote

import mz.co.bilheteira.api.domain.local.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NasaApiService {
    @GET("mars-photos/api/v1/rovers/{name}/photos")
    suspend fun getAllRoversPhotos(
        @Path("name") roverName:String,
        @Query("earth_date") earthDate: String,
        @Query("sol") sol: Int,
        @Query("api_key") privateKey: String
    ): Response<ApiResponse>
}
