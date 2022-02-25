package com.example.paginationInRecyclerView.models

import com.google.gson.annotations.SerializedName


//use POJO extension to create model from json in a second
data class PhotoItem(
    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("width")
    val width: Int? = null,

    @field:SerializedName("download_url")
    val downloadUrl: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("height")
    val height: Int? = null
)
