package mz.co.bilheteira.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mz.co.bilheteira.storage.dao.NasaDao
import mz.co.bilheteira.storage.entity.PhotoEntity
import mz.co.bilheteira.storage.entity.converters.Converters

@Database(entities = [PhotoEntity::class], version = 1)
@TypeConverters(Converters::class)
internal abstract class NasaDatabase : RoomDatabase() {
    abstract fun getNasaDao(): NasaDao

    companion object {
        const val DB_NAME = "nasa.db"
    }
}
