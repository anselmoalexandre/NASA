package mz.co.bilheteira.domain.repository

import mz.co.bilheteira.api.domain.local.ApiResponse
import mz.co.bilheteira.api.domain.local.RemoteCamera
import mz.co.bilheteira.api.domain.local.RemotePhotos
import mz.co.bilheteira.api.domain.local.RemoteRover
import mz.co.bilheteira.api.domain.remote.NasaApiService
import retrofit2.Response

class FakeNasaApiService : NasaApiService {
    override suspend fun getAllRoversPhotos(
        roverName: String,
        earthDate: String,
        sol: Int,
        privateKey: String
    ): Response<ApiResponse> = Response.success(
        200,
        ApiResponse(
            listOf(
                RemotePhotos(
                    id = 669,
                    sol = 100,
                    earth_date = "2016-09-08",
                    img_src = "https://nasa.api.gov/rovers/2016/09/08",
                    camera = RemoteCamera(
                        id = 20,
                        name = "FHAZ",
                        full_name = "Front Hazard Avoidance Camera",
                        rover_id = 5
                    ),
                    rover = RemoteRover(
                        id = 5,
                        name = "Curiosity",
                        status = "active",
                        landing_date = "2012-08-06",
                        launch_date = "2011-11-26"
                    )
                )
            )
        )
    )
}