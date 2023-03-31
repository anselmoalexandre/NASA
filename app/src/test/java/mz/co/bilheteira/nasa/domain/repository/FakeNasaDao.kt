package mz.co.bilheteira.nasa.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mz.co.bilheteira.storage.entity.PhotoEntity

class FakeNasaDao {
    fun getPhotos(): Flow<List<PhotoEntity>> = flow {
        emit(emptyList())
    }
}