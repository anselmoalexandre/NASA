package mz.co.bilheteira.storage.entity.converters

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import mz.co.bilheteira.storage.data.CameraEntity
import mz.co.bilheteira.storage.data.RoverEntity

class Converters {
    private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val roverAdapter: JsonAdapter<RoverEntity> = moshi.adapter(RoverEntity::class.java)
    private val cameraAdapter: JsonAdapter<CameraEntity> = moshi.adapter(CameraEntity::class.java)


    @TypeConverter
    fun fromRoverEntity(roverEntity: RoverEntity): String = roverAdapter.toJson(roverEntity)

    @TypeConverter
    fun toRoverEntity(json: String): RoverEntity? = roverAdapter.fromJson(json)

    @TypeConverter
    fun fromCameraEntity(cameraEntity: CameraEntity): String = cameraAdapter.toJson(cameraEntity)

    @TypeConverter
    fun toCameraEntity(json: String): CameraEntity? = cameraAdapter.fromJson(json)
}