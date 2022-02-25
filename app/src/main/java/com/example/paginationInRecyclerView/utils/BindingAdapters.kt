package com.example.paginationInRecyclerView.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.paginationInRecyclerView.R

@BindingAdapter("img_src")
fun ImageView.setImageUrl(url: String) {
    Glide.with(this.context).load(url).placeholder(R.drawable.ic_placeholder).error(R.drawable.ic_error).into(this)
}