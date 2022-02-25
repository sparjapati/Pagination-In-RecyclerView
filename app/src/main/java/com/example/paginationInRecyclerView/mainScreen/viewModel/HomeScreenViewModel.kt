package com.example.paginationInRecyclerView.mainScreen.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paginationInRecyclerView.models.PhotoItem
import com.example.paginationInRecyclerView.networks.PhotoApi
import com.example.paginationInRecyclerView.utils.Constants.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreenViewModel : ViewModel() {

    private val _photoItems: MutableLiveData<ArrayList<PhotoItem?>> = MutableLiveData(ArrayList())
    val photoItems: LiveData<ArrayList<PhotoItem?>>
        get() = _photoItems

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private var currPage = 1

    init {
        fetchPhotoItems()
    }

    private fun fetchPhotoItems() {
        startLoading()
        PhotoApi.apiService.getPhotoItems(currPage).enqueue(object : Callback<ArrayList<PhotoItem>> {
            override fun onResponse(call: Call<ArrayList<PhotoItem>>, response: Response<ArrayList<PhotoItem>>) {
//                Log.d(TAG, "onResponse: ${response.body()?.size} items found")
//                Log.d(TAG, "onResponse: ${_photoItems.value!!.size}")
                stopLoading()
                val list = _photoItems.value!!
                response.body()?.let { list.addAll(it) }
                _photoItems.postValue(list)
//                Log.d(TAG, "onResponse: ${_photoItems.value!!.size}")
            }

            override fun onFailure(call: Call<ArrayList<PhotoItem>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                stopLoading()
            }
        })
    }

    fun loadMoreItems() {
        currPage++
        fetchPhotoItems()
    }


    private fun startLoading() {
        _isLoading.value = true
        val list = _photoItems.value!!
        list.add(null)
        _photoItems.postValue(list)
    }

    private fun stopLoading() {
        _isLoading.value = false
        val list = _photoItems.value!!
        list.removeLast()
        _photoItems.postValue(list)
    }
}