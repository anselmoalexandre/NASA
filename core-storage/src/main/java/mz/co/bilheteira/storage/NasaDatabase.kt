package mz.co.bilheteira.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import mz.co.bilheteira.storage.dao.NasaDao
import mz.co.bilheteira.storage.entities.CameraEntity
import mz.co.bilheteira.storage.entities.PhotoEntity
import mz.co.bilheteira.storage.entities.RoverEntity

@Database(entities = [PhotoEntity::class, RoverEntity::class, CameraEntity::class], version = 1)
internal abstract class NasaDatabase : RoomDatabase() {
    abstract fun getNasaDao(): NasaDao

    companion object {
        const val DB_NAME = "nasa.db"
    }
}
