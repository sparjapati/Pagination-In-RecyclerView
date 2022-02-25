package com.example.paginationInRecyclerView.mainScreen.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paginationInRecyclerView.models.PhotoItem

class HomeScreenViewModel : ViewModel() {

    private val _photoItems: MutableLiveData<ArrayList<PhotoItem>> = MutableLiveData(ArrayList())
    val photoItems: LiveData<ArrayList<PhotoItem>>
        get() = _photoItems

    init {
        fetchPhotoItems()
    }

    private fun fetchPhotoItems() {
        // inserting dummy data
        for (i in 1..20)
            _photoItems.value!!.add(PhotoItem("Harry Potter", "https://picsum.photos/200/300", i))
    }

}