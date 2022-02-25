package com.example.paginationInRecyclerView.mainScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paginationInRecyclerView.databinding.ActivityMainBinding
import com.example.paginationInRecyclerView.mainScreen.adapter.HomeScreenRecyclerViewAdapter
import com.example.paginationInRecyclerView.models.PhotoItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: HomeScreenRecyclerViewAdapter
    private val photoItemList = ArrayList<PhotoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()

        fetchPhotoItems()
    }

    private fun setAdapter() {
        adapter = HomeScreenRecyclerViewAdapter(photoItemList)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvItems.layoutManager = layoutManager
        binding.rvItems.adapter = adapter
    }


    private fun fetchPhotoItems() {
        // inserting dummy data
        for (i in 1..20)
            photoItemList.add(PhotoItem("Harry Potter", "https://picsum.photos/200/300", i))
        adapter.notifyDataSetChanged()
    }
}