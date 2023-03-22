package mz.co.bilheteira.nasa.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import mz.co.bilheteira.domain.data.PhotoModel
import mz.co.bilheteira.nasa.databinding.ItemGenericListBinding

internal class RoverAdapter(
    private val selectedPhoto: (PhotoModel) -> (Unit)
) : ListAdapter<PhotoModel, RoverAdapter.RoverViewHolder>(DIFF_UTILS) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoverViewHolder {
        val view = ItemGenericListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RoverViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoverViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RoverViewHolder(
        private val binding: ItemGenericListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener { selectedPhoto(getItem(adapterPosition)) }
        }

        fun bind(photoModel: PhotoModel) = binding.apply {
            photo.load(photoModel.photo)
            rover.text = photoModel.rover.name
            camera.text = photoModel.camera.name
            date.text = photoModel.earthDate
        }
    }

    companion object {
        private val DIFF_UTILS = object : DiffUtil.ItemCallback<PhotoModel>() {
            override fun areItemsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
                return oldItem.rover == newItem.rover
            }
        }
    }
}