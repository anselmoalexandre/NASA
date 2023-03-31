package mz.co.bilheteira.compose.screens.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import mz.co.bilheteira.compose.R
import mz.co.bilheteira.compose.screens.details.DetailsViewModel.PhotoUIState
import mz.co.bilheteira.compose.ui.NasaTopBar

@ExperimentalMaterial3Api
@Composable
internal fun RoverDetailsRoute(
    photoId: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    viewModel.fetchPhotoDetailsFromLocalStorage(photoId)

    RoverDetailsScreen(
        uiState = uiState,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoverDetailsScreen(
    uiState: PhotoUIState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState) {
            PhotoUIState.Loading -> {}
            is PhotoUIState.Error -> RoverDetailsErrorScreen()
            is PhotoUIState.Content -> RoverDetailsContent(
                photoUrl = uiState.photoModel.photo,
                roverName = uiState.photoModel.rover.name,
                launchDate = uiState.photoModel.rover.launchDate,
                landingDate = uiState.photoModel.rover.landingDate,
                cameraName = uiState.photoModel.camera.name,
                cameraFullName = uiState.photoModel.camera.fullName,
                modifier = modifier
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun RoverDetailsContent(
    photoUrl: String,
    roverName: String,
    launchDate: String,
    landingDate: String,
    cameraName: String,
    cameraFullName: String,
    modifier: Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary)
    ) {
        Column {
            NasaTopBar(titleRes = R.string.photo_details)

            Row { PhotoIcon(url = photoUrl) }

            Box(
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .border(BorderStroke(width = 1.dp, Color.DarkGray))
                        .padding(start = 20.dp)
                ) {
                    Spacer(modifier = Modifier.padding(12.dp))
                    Row {
                        DetailsTitle(
                            title = stringResource(id = R.string.photo_details),
                            modifier = modifier
                        )
                    }

                    Spacer(modifier = Modifier.padding(12.dp))

                    Row {
                        PhotoDetails(
                            roverName = roverName,
                            launchDate = launchDate,
                            landingDate = landingDate,
                            cameraName = cameraName,
                            cameraFullName = cameraFullName,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PhotoDetails(
    roverName: String,
    launchDate: String,
    landingDate: String,
    cameraName: String,
    cameraFullName: String
) {
    Column {
        Row {
            Text(text = roverName, style = MaterialTheme.typography.headlineMedium)
        }
        Spacer(modifier = Modifier.padding(12.dp))

        Row {
            Text(text = launchDate, style = MaterialTheme.typography.headlineMedium)
        }
        Spacer(modifier = Modifier.padding(12.dp))

        Row {
            Text(text = landingDate, style = MaterialTheme.typography.headlineMedium)
        }
        Spacer(modifier = Modifier.padding(12.dp))

        Row {
            Text(text = cameraName, style = MaterialTheme.typography.headlineMedium)
        }
        Spacer(modifier = Modifier.padding(12.dp))

        Row {
            Text(text = cameraFullName, style = MaterialTheme.typography.headlineMedium)
        }
        Spacer(modifier = Modifier.padding(12.dp))
    }
}

@Composable
private fun PhotoIcon(url: String) {
    AsyncImage(
        model = url,
        contentDescription = stringResource(id = R.string.photo_description),
        contentScale = ContentScale.Crop,
        placeholder = if (LocalInspectionMode.current) {
            null
        } else null,
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    )
}

@Composable
private fun DetailsTitle(
    title: String,
    modifier: Modifier
) {
    Text(text = title, modifier = modifier, style = MaterialTheme.typography.headlineMedium)
}

@Composable
private fun RoverDetailsErrorScreen() {
    Text(text = stringResource(id = R.string.error))
}
