package mz.co.bilheteira.nasa.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mz.co.bilheteira.domain.data.PhotoModel
import mz.co.bilheteira.domain.data.toPhotoModel
import mz.co.bilheteira.domain.repository.NasaRepository
import mz.co.bilheteira.utils.handleThrowable
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RoverListViewModel @Inject constructor(
    private val nasaRepository: NasaRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<RoverUIState>(RoverUIState.Loading)
    val uiState: StateFlow<RoverUIState> = _uiState.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.value = RoverUIState.Error(message = exception.handleThrowable())
    }

    init {
        fetchRoversPhotosFromLocalStorage()
    }

    private fun fetchRoversPhotosFromLocalStorage() {
        _uiState.update { RoverUIState.Loading }
        viewModelScope.launch(exceptionHandler) {
            nasaRepository.getLocalRoversPhotos().collect { listOfPhotos ->
                if (listOfPhotos.isNotEmpty()) {
                    _uiState.update {
                        RoverUIState.Content(rovers = listOfPhotos)
                    }
                } else fetchRemotePhotos()
            }
        }
    }


    private suspend fun fetchRemotePhotos(rover: String = DEFAULT_ROVER) {
        val response = nasaRepository.getAllRemoteRoversPhotos(
            roverName = rover,
            earthDate = EARTH_DATE,
            sol = 10,
            privateKey = MY_PRIVATELY_STORED_KEY
        )

        if (response.isSuccessful) {
            response.body()?.let {
                _uiState.update { RoverUIState.Success }
                handleRemoteData(photos = it.photos.map { remotePhotos ->
                    remotePhotos.toPhotoModel()
                })
            } ?: _uiState.update { RoverUIState.Error(message = response.message()) }
        } else _uiState.update { RoverUIState.Error(message = response.message()) }
    }

    private suspend fun handleRemoteData(photos: List<PhotoModel>) {
        _uiState.update { RoverUIState.Content(rovers = photos) }
        cacheDataOnLocalStorage(photos)
    }

    private suspend fun cacheDataOnLocalStorage(photos: List<PhotoModel>) {
        photos.forEach { nasaRepository.insertPhoto(it) }
    }

    fun filterBySpecificRover() {
        val rovers = listOf("Curiosity", "Spirit", "Opportunity")

        val filterPhotosOf = rovers.random()

        _uiState.update { RoverUIState.Loading }
        viewModelScope.launch(exceptionHandler) {
            nasaRepository.getLocalRoversPhotos().collect { listOfPhotos ->
                val filteredPhotos = listOfPhotos.filter {
                    it.rover.name == filterPhotosOf
                }

                if (filteredPhotos.isNotEmpty()) {
                    _uiState.update {
                        RoverUIState.Content(rovers = filteredPhotos)
                    }
                } else fetchRemotePhotos(rover = filterPhotosOf)
            }
        }
    }

    sealed interface RoverUIState {
        object Loading : RoverUIState
        object Success : RoverUIState
        data class Content(val rovers: List<PhotoModel>) : RoverUIState
        data class Error(val message: Any) : RoverUIState
    }

    companion object {
        private const val DEFAULT_ROVER = "spirit"
        private const val EARTH_DATE = "2012-08-16"
        private const val MY_PRIVATELY_STORED_KEY = "MdF2HtI7mJWZbVnd5lXfpZCfcISRIw8vL1wEl0Nl"
    }
}
