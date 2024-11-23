package com.amir.aram.feature.courseActivity

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.amir.aram.databinding.ItemCorseBinding
import com.amir.aram.model.dataClass.CourseItem
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class CourseAdapter(
    private val courseList: ArrayList<CourseItem.CourseItemItem>,
    private val listener: CourseItemClickListener,
    private val glide: RequestManager
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    private lateinit var binding: ItemCorseBinding

    inner class CourseViewHolder(private val binding: ItemCorseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindViews(course: CourseItem.CourseItemItem) {
            binding.apply {
                courseTitle.text = course.titile
                courseSession.text = course.sessions
                glide.load(course.image)
                    .into(imageViewCourse)
                root.setOnClickListener {
                    listener.onCourseItemClicked(course.id, course.titile, course.image2)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        binding = ItemCorseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bindViews(courseList[position])
    }

    override fun getItemCount(): Int = courseList.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<CourseItem.CourseItemItem>) {
        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize() = courseList.size
            override fun getNewListSize() = newList.size
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                courseList[oldItemPosition].id == newList[newItemPosition].id

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                courseList[oldItemPosition] == newList[newItemPosition]
        }
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        courseList.clear()
        courseList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    interface CourseItemClickListener {
        fun onCourseItemClicked(courseId: Int, title: String, image2: String)
    }
}


