package mz.co.bilheteira.storage.dao

import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import mz.co.bilheteira.storage.entity.PhotoEntity
import org.junit.Test

import org.junit.Before

@ExperimentalCoroutinesApi
class NasaDaoTest {
    @MockK
    private lateinit var nasaDaoMockK: NasaDao
    @MockK
    private lateinit var photoEntityMockK: PhotoEntity

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `insert or update photo `() = runTest {
        // Given
        coEvery { nasaDaoMockK.insertPhoto(photoEntityMockK) } just runs

        // When
        nasaDaoMockK.insertPhoto(photoEntityMockK)

        // Then
        coVerify(atLeast = 1, atMost = 1) { nasaDaoMockK.insertPhoto(photoEntityMockK) }
    }

    @Test
    fun `get all rover photos locally stored`() = runTest {
        // Given
        coEvery { nasaDaoMockK.getPhotos() } returns flow { emit(listOf(photoEntityMockK)) }

        // When
        val photos = nasaDaoMockK.getPhotos().first()

        // Then
        assertThat(photos).isNotEmpty()
    }

    @Test
    fun `try to get all rover photos locally stored and return empty`() = runTest {
        // Given
        coEvery { nasaDaoMockK.getPhotos() } returns flow { emit(emptyList()) }

        // When
        val photos = nasaDaoMockK.getPhotos()

        // Then
        assertThat(photos.first()).isEmpty()
    }
}