package com.example.paginationInRecyclerView.mainScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationInRecyclerView.databinding.LoadMoreProgressBinding
import com.example.paginationInRecyclerView.databinding.PhotoItemBinding
import com.example.paginationInRecyclerView.models.PhotoItem

class HomeScreenRecyclerViewAdapter : ListAdapter<PhotoItem?, RecyclerView.ViewHolder>(Callbacks) {

    companion object {
        private const val LOADER: Int = 1
        private const val ITEM: Int = 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position] == null)
            LOADER
        else
            ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == LOADER)
            LoadingItemViewHolder.from(parent)
        else
            PhotoItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoItemViewHolder)
            currentList[position]?.let { holder.bind(it) }
    }

    //    why I override this method
    //    check this -> https://stackoverflow.com/questions/49726385/listadapter-not-updating-item-in-recyclerview
    //    and this -> https://stackoverflow.com/a/57848424/12388845
    override fun submitList(list: List<PhotoItem?>?) {

        super.submitList(list?.let { ArrayList(it) })
//        Log.d(TAG, "submitList: list changed")
    }
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

class LoadingItemViewHolder(private val binding: LoadMoreProgressBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(viewGroup: ViewGroup): LoadingItemViewHolder {
            val binding = LoadMoreProgressBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return LoadingItemViewHolder(binding)
        }
    }
}

object Callbacks : DiffUtil.ItemCallback<PhotoItem?>() {
    override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean {
        return oldItem == newItem
    }

}