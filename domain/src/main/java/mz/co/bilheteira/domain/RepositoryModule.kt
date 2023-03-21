package mz.co.bilheteira.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import mz.co.bilheteira.api.domain.remote.NasaApiService
import mz.co.bilheteira.domain.repository.NasaRepository
import mz.co.bilheteira.domain.repository.NasaRepositoryImpl
import mz.co.bilheteira.storage.dao.NasaDao

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideRepository(
        nasaDao: NasaDao,
        nasaApiService: NasaApiService
    ): NasaRepository = NasaRepositoryImpl(nasaDao, nasaApiService)
}
