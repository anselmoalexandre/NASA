package mz.co.bilheteira.storage.entity.converters

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import mz.co.bilheteira.storage.data.CameraEntity
import mz.co.bilheteira.storage.data.RoverEntity
import java.lang.reflect.ParameterizedType

class Converters {
    private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    private val roverType: ParameterizedType = Types.newParameterizedType(RoverEntity::class.java)
    private val roverAdapter: JsonAdapter<RoverEntity> = moshi.adapter(roverType)

    private val cameraType: ParameterizedType = Types.newParameterizedType(CameraEntity::class.java)
    private val cameraAdapter: JsonAdapter<CameraEntity> = moshi.adapter(cameraType)


    @TypeConverter
    fun fromRoverEntity(roverEntity: RoverEntity): String = roverAdapter.toJson(roverEntity)

    @TypeConverter
    fun toRoverEntity(json: String): RoverEntity? = roverAdapter.fromJson(json)

    @TypeConverter
    fun fromCameraEntity(cameraEntity: CameraEntity):String = cameraAdapter.toJson(cameraEntity)

    @TypeConverter
    fun toCameraEntity(json:String):CameraEntity? = cameraAdapter.fromJson(json)
}