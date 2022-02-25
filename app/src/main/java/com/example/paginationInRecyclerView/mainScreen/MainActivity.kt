package com.example.paginationInRecyclerView.mainScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paginationInRecyclerView.databinding.ActivityMainBinding
import com.example.paginationInRecyclerView.mainScreen.adapter.HomeScreenRecyclerViewAdapter
import com.example.paginationInRecyclerView.mainScreen.viewModel.HomeScreenViewModel
import com.example.paginationInRecyclerView.models.PhotoItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var adapter: HomeScreenRecyclerViewAdapter
    private val photoItemList = ArrayList<PhotoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[HomeScreenViewModel::class.java]
        setAdapter()

        viewModel.photoItems.observe(this) { itemList ->
            photoItemList.addAll(itemList)
            adapter.notifyDataSetChanged()
        }
    }

    private fun setAdapter() {
        adapter = HomeScreenRecyclerViewAdapter(photoItemList)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvItems.layoutManager = layoutManager
        binding.rvItems.adapter = adapter
    }
}