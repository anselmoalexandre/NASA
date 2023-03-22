package mz.co.bilheteira.nasa.ui.list

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
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mz.co.bilheteira.domain.data.PhotoModel
import mz.co.bilheteira.nasa.R
import mz.co.bilheteira.nasa.databinding.FragmentListBinding
import mz.co.bilheteira.nasa.ui.adapter.RoverAdapter
import mz.co.bilheteira.nasa.ui.list.RoverListViewModel.RoverUIState

@AndroidEntryPoint
class RoverListFragment : Fragment(R.layout.fragment_list) {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RoverListViewModel by viewModels()

    private val adapter by lazy {
        RoverAdapter { onPhotoSelection(it.id) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupObservers()
        setupClickListeners()
    }

    private fun setupAdapter() {
        binding.recycler.adapter = adapter
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        RoverUIState.Loading -> renderLoading()
                        RoverUIState.Success -> renderSuccess()
                        is RoverUIState.Error -> renderError(it.message)
                        is RoverUIState.Content -> renderContent(it.rovers)
                    }
                }
            }
        }
    }

    private fun setupClickListeners() {
        binding.filterFab.setOnClickListener { viewModel.filterBySpecificRover() }
    }

    private fun renderLoading() {
        binding.apply {
            loading.isVisible = true
            filterFab.isGone = true
        }
    }

    private fun renderSuccess() {
        binding.apply {
            loading.isGone = true
            filterFab.isVisible = true
        }
    }

    private fun renderError(message: Any) {
        renderSuccess()
        Toast.makeText(requireContext(), message.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun renderContent(photos: List<PhotoModel>) {
        renderSuccess()
        adapter.submitList(photos)
    }

    private fun onPhotoSelection(photoId: Int) {
        findNavController().navigate(RoverListFragmentDirections.toDetailsFragment(photoId))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}