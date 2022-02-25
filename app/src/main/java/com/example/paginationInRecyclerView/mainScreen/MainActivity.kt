package com.example.paginationInRecyclerView.mainScreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationInRecyclerView.databinding.ActivityMainBinding
import com.example.paginationInRecyclerView.mainScreen.adapter.HomeScreenRecyclerViewAdapter
import com.example.paginationInRecyclerView.mainScreen.viewModel.HomeScreenViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var adapter: HomeScreenRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[HomeScreenViewModel::class.java]
        setAdapter()

        viewModel.photoItems.observe(this) { photoItemArrayList ->
//            Log.d(TAG, "onCreate: submitting new list")
            adapter.submitList(photoItemArrayList)
        }
    }

    private fun setAdapter() {
        adapter = HomeScreenRecyclerViewAdapter()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvItems.layoutManager = layoutManager
        binding.rvItems.adapter = adapter

        binding.rvItems.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val llm = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItems = llm.findLastCompletelyVisibleItemPosition()
                if (!(viewModel.isLoading.value)!! && lastVisibleItems >= viewModel.photoItems.value!!.size - 2) {
//                    Log.d(TAG, "onScrolled: loading new items")
                    viewModel.loadMoreItems()
                }
            }
        })
    }
}