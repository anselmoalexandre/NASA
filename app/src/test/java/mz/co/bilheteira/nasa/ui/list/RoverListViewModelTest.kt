package mz.co.bilheteira.nasa.ui.list

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import mz.co.bilheteira.nasa.domain.repository.FakeNasaApiService
import mz.co.bilheteira.nasa.domain.repository.FakeNasaDao
import mz.co.bilheteira.nasa.domain.repository.FakeNasaRepository
import org.junit.Test

@ExperimentalCoroutinesApi
class RoverListViewModelTest {

    private val fakeNasaApiService = FakeNasaApiService()
    private val fakeNasaDao = FakeNasaDao()

    private val fakeNasaRepository = FakeNasaRepository(
        nasaFakeService = fakeNasaApiService,
        nasaFakeNasaDao = fakeNasaDao
    )


    @Test
    fun `fetch rovers from local storage`() = runTest {
        // Given
        val localRovers = fakeNasaRepository.getLocalRoversPhotos()

        // When
        val firstCollected = localRovers.first()

        // Then
        assertThat(firstCollected).isEmpty()
    }

    @Test
    fun `fetch rovers from remote api`() = runTest {
        // Given
        val remoteRovers = fakeNasaRepository.getAllRemoteRoversPhotos(
            roverName = "Curiosity",
            earthDate = "2016-09-08",
            sol = 100,
            privateKey = "MY_PRIVATELY_STORED_KEY"
        )

        // When
        val rover = remoteRovers.body()

        // Then
        assertThat(rover?.photos?.first()?.camera?.name).isEqualTo("FHAZ")
    }
}