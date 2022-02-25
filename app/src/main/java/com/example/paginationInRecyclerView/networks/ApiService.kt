package com.example.paginationInRecyclerView.networks

import com.example.paginationInRecyclerView.models.PhotoItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(NetworkConfigurations.VARIABLE_PHOTOS_ENDPOINT)
    fun getPhotoItems(@Query(NetworkConfigurations.QUERY_PAGE) page: Int = 1, @Query(NetworkConfigurations.QUERY_LIMIT) limit: Int = NetworkConfigurations.DEFAULT_PHOTO_ITEMS_LIMIT): Call<ArrayList<PhotoItem>>
}
