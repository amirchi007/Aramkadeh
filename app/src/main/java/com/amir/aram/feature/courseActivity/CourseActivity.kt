package com.amir.aram.feature.courseActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.amir.aram.databinding.ActivityCourseBinding
import com.amir.aram.feature.sessionActivity.SessionActivity
import com.amir.aram.model.dataClass.ChipItem
import com.amir.aram.model.dataClass.CourseItem
import com.bumptech.glide.RequestManager
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CourseActivity : AppCompatActivity(), CourseAdapter.CourseItemClickListener {
    private lateinit var binding: ActivityCourseBinding
    private lateinit var adapter: CourseAdapter
    private val courseViewModel: CourseViewModel by viewModels()
    @Inject
    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
        getCategoryFromApi()
    }

    override fun onResume() {
        super.onResume()
        fetchCourseItem(5) // Load the first category on resume
    }

    private fun setupRecyclerView() {
        adapter = CourseAdapter(arrayListOf(), this, glide)
        binding.recyclerCourse.adapter = adapter
        binding.recyclerCourse.layoutManager = GridLayoutManager(this, 2)
    }

    private fun setDataToRecycler(data: List<CourseItem.CourseItemItem>) {
        adapter.updateData(data)
    }

    private fun updateChipGroup(chipItems: List<ChipItem.ChipItemItem>) {
        binding.chipGroup.removeAllViews()
        val reserveItem = chipItems.reversed()
        for ((index, item) in reserveItem.withIndex()) {
            val chip = Chip(this).apply {
                text = item.titile
                isCheckable = true
                setOnClickListener {
                    fetchCourseItem(item.id)
                }
            }
            if (index == 0) {
                chip.isChecked = true
                fetchCourseItem(item.id)
            }
            binding.chipGroup.addView(chip)
        }
    }

    private fun getCategoryFromApi() {
        courseViewModel.getCategoryItem()
    }

    private fun fetchCourseItem(catId: Int) {
        courseViewModel.getCourseItem(catId)
    }

    private fun observeViewModel() {
        courseViewModel.categoryItems.observe(this) { chipItems ->
            updateChipGroup(chipItems)
        }

        courseViewModel.courseItems.observe(this) { courseItems ->
            setDataToRecycler(courseItems)
        }

        courseViewModel.progressBar.observe(this) { isLoading ->
            binding.progressMain.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        courseViewModel.errorMessage.observe(this) { message ->
            showToast("Error: $message")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCourseItemClicked(courseId: Int, titile: String, image2: String) {
        openSessionActivity(courseId, titile, image2)
    }

    private fun openSessionActivity(courseId: Int, titile: String, image2: String) {
        val intent = Intent(this, SessionActivity::class.java).apply {
            putExtra("id", courseId)
            putExtra("title", titile)
            putExtra("image2", image2)
        }
        Log.v("CourseActivity", "Image URL: $image2, Title: $titile")
        startActivity(intent)
    }
}

