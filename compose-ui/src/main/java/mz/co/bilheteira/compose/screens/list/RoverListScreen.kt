package mz.co.bilheteira.compose.screens.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import mz.co.bilheteira.compose.R
import mz.co.bilheteira.compose.screens.list.RoverListViewModel.RoverUIState
import mz.co.bilheteira.compose.ui.NasaTopBar
import mz.co.bilheteira.domain.data.PhotoModel

@Composable
internal fun RoverListRoute(
    modifier: Modifier = Modifier,
    onRoverPhotoClick: (PhotoModel) -> Unit,
    viewModel: RoverListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RoverListScreen(
        uiState = uiState,
        modifier = modifier,
        onRoverPhotoClick = onRoverPhotoClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RoverListScreen(
    uiState: RoverUIState,
    modifier: Modifier = Modifier,
    onRoverPhotoClick: (PhotoModel) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        NasaTopBar(titleRes = R.string.photos)

        Spacer(modifier = Modifier.padding(10.dp))

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState) {
                RoverUIState.Loading -> {}
                is RoverUIState.Success -> {}
                is RoverUIState.Error -> RoverPhotosErrorScreen()
                is RoverUIState.Content -> RoverListContent(
                    modifier = modifier,
                    list = uiState.rovers,
                    onRoverPhotoClick = onRoverPhotoClick
                )
            }
        }
    }
}

@Composable
private fun RoverPhotosErrorScreen() {
    Text(text = stringResource(id = R.string.error))
}

@Composable
private fun RoverListContent(
    modifier: Modifier,
    list: List<PhotoModel>,
    onRoverPhotoClick: (PhotoModel) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 8.dp)
    ) {
        list.forEach { photoModel ->
            val photoId = photoModel.id
            item(key = photoId) {
                RoverPhotoItem(
                    roverName = photoModel.rover.name,
                    camera = photoModel.camera.name,
                    earthDate = photoModel.earthDate,
                    photoUrl = photoModel.photo,
                    onClick = { onRoverPhotoClick(photoModel) },
                    modifier = modifier
                )
            }
        }

        item {
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
        }
    }
}

@Composable
private fun RoverPhotoItem(
    roverName: String,
    camera: String,
    earthDate: String,
    photoUrl: String,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(BorderStroke(1.dp, Color.LightGray))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .clickable { onClick() }
                .padding(vertical = 16.dp)
        ) {
            RoversPhotoIcon(
                photoUrl = photoUrl,
                modifier = modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(80.dp))
            RoverPhotoContent(
                roverName = roverName,
                camera = camera,
                earthDate = earthDate
            )
        }
    }

    Spacer(modifier = modifier.padding(top = 10.dp))
}


@Composable
private fun RoversPhotoIcon(photoUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = photoUrl,
        contentDescription = stringResource(id = R.string.photo_description),
        modifier = modifier
    )
}

@Composable
private fun RoverPhotoContent(
    roverName: String,
    camera: String,
    earthDate: String,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = roverName,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 5.dp)
        )

        Text(
            text = camera,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 5.dp)
        )

        Text(
            text = earthDate,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 5.dp)
        )
    }
}