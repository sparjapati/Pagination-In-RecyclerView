package com.example.paginationInRecyclerView.mainScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationInRecyclerView.databinding.PhotoItemBinding
import com.example.paginationInRecyclerView.models.PhotoItem

class HomeScreenRecyclerViewAdapter(private val photoItemsList: ArrayList<PhotoItem>) : RecyclerView.Adapter<PhotoItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemViewHolder {
        return PhotoItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PhotoItemViewHolder, position: Int) {
        holder.bind(photoItemsList[position])
    }

    override fun getItemCount(): Int = photoItemsList.size
}

class PhotoItemViewHolder(private val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PhotoItem) {
        binding.photoItem = item
    }

    companion object {
        fun from(viewGroup: ViewGroup): PhotoItemViewHolder {
            val binding = PhotoItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return PhotoItemViewHolder(binding)
        }
    }
}