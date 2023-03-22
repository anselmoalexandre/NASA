package mz.co.bilheteira.nasa.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mz.co.bilheteira.domain.data.PhotoModel
import mz.co.bilheteira.nasa.R
import mz.co.bilheteira.nasa.databinding.FragmentDetailsBinding
import mz.co.bilheteira.nasa.ui.details.DetailsViewModel.PhotoUIState

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    private val viewModel: DetailsViewModel by viewModels()

    private val photoId by lazy { args.photoId }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchPhotoDetails()
        setupObservers()
    }

    private fun fetchPhotoDetails() = viewModel.fetchPhotoDetailsFromLocalStorage(photoId)

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        PhotoUIState.Loading -> renderLoading()
                        is PhotoUIState.Error -> renderError(it.message)
                        is PhotoUIState.Content -> renderContent(it.rover)
                    }
                }
            }
        }
    }

    private fun renderLoading() {
        binding.loading.isVisible = true
    }

    private fun renderError(message: Any) {
        binding.loading.isGone = true
        Toast.makeText(requireContext(), message.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun renderContent(photoDetails: PhotoModel) {
        binding.apply {
            photo.load(photoDetails.photo)
            rover.text = photoDetails.rover.name
            launchDate.text = photoDetails.rover.launchDate
            landingDate.text = photoDetails.rover.landingDate
            camera.text = photoDetails.camera.name
            cameraFullName.text = photoDetails.camera.fullName
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}