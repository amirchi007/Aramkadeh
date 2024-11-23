package com.amir.aram.feature.sessionActivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.amir.aram.databinding.ActivitySessionBinding
import com.amir.aram.util.showToast
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SessionActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySessionBinding
    private lateinit var adapter: SessionAdapter
    private val sessionViewModel: SessionViewModel by viewModels()
    @Inject
    lateinit var glide: RequestManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()

        val id = intent.getIntExtra("id", -1)
        val image2 = intent.getStringExtra("image2")
        val title = intent.getStringExtra("title")


        glide.load(image2 ?: "default_image_url_here").into(binding.headerImage)

        binding.courseTitle.text = title ?: "Unknown Title"
        if (id != -1) {
            sessionViewModel.getSessionItem(id)
        } else {
            showToast("Invalid ID")
        }
    }

    private fun setupRecyclerView() {
        adapter = SessionAdapter(arrayListOf())
        binding.sessionRecyclerView.adapter = adapter
        binding.sessionRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun observeViewModel() {
        sessionViewModel.sessionItems.observe(this) { sessionItems ->
            adapter.updateData(sessionItems)
        }

        sessionViewModel.errorMessage.observe(this) { message ->
            showToast("Error: $message")
        }
    }
}



