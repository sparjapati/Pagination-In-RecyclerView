package com.example.paginationInRecyclerView.networks

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitProvider {
    private val retrofitClient by lazy { Retrofit.Builder().baseUrl(NetworkConfigurations.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build() }

    fun getClient(): Retrofit {
        return retrofitClient
    }
}