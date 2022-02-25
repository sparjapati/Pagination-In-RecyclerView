package com.example.paginationInRecyclerView.networks

object PhotoApi {
    val apiService: ApiService by lazy {
        RetroFitProvider.getClient().create(ApiService::class.java)
    }
}