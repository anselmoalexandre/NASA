package mz.co.bilheteira.nasa.ui.details

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
import mz.co.bilheteira.domain.repository.NasaRepository
import mz.co.bilheteira.utils.handleThrowable
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val nasaRepository: NasaRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<PhotoUIState>(PhotoUIState.Loading)
    val uiState: StateFlow<PhotoUIState> = _uiState.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
        _uiState.value = PhotoUIState.Error(message = exception.handleThrowable())
    }

    fun fetchPhotoDetailsFromLocalStorage(photoId: Int) {
        _uiState.update { PhotoUIState.Loading }
        viewModelScope.launch(exceptionHandler) {
            nasaRepository.getLocalRoversPhotos().collect { photos ->
                photos.firstOrNull {
                    it.id == photoId
                }?.let { photo ->
                    _uiState.update {
                        PhotoUIState.Content(photo)
                    }
                } ?: _uiState.update {
                    PhotoUIState.Error(message = "Photo unavailable")
                }
            }
        }
    }

    sealed interface PhotoUIState {
        object Loading : PhotoUIState
        data class Error(val message: Any) : PhotoUIState
        data class Content(val rover: PhotoModel) : PhotoUIState
    }
}