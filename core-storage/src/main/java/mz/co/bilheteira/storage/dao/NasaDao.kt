package mz.co.bilheteira.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import mz.co.bilheteira.storage.entities.CameraEntity
import mz.co.bilheteira.storage.entities.PhotoEntity

@Dao
interface NasaDao {
    @Query("SELECT * FROM photo")
    fun getPhotos(): Flow<PhotoEntity>

    @Query("SELECT * FROM camera WHERE rover_id= :roverId")
    fun getCameras(roverId: Int): Flow<CameraEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photoEntity: PhotoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCamera(cameraEntity: CameraEntity)
}
