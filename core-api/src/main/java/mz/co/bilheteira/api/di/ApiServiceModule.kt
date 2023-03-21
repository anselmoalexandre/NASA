package mz.co.bilheteira.api.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mz.co.bilheteira.api.domain.remote.NasaApiService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object ApiServiceModule {

    @Provides
    fun provideNasaRoverPhotosApiService(
        retrofit: Retrofit
    ): NasaApiService = retrofit.create(NasaApiService::class.java)
}
