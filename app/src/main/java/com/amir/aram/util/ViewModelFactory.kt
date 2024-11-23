package com.amir.aram.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amir.aram.feature.courseActivity.CourseViewModel
import com.amir.aram.feature.sessionActivity.SessionViewModel
import com.amir.aram.model.repository.CourseRepository
import com.amir.aram.model.repository.SessionRepository

class ViewModelFactory(private val courseRepository: CourseRepository,private val sessionRepository: SessionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CourseViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                CourseViewModel(courseRepository) as T
            }
            modelClass.isAssignableFrom(SessionViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                SessionViewModel(sessionRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
