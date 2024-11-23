package com.amir.aram.model.repository

import com.amir.aram.model.ApiService
import com.amir.aram.model.dataClass.ChipItem
import com.amir.aram.model.dataClass.CourseItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CourseRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getCategoryItem(): List<ChipItem.ChipItemItem> {
        return apiService.getCategoryItem()
    }

    suspend fun getCourseItem(catId:Int): List<CourseItem.CourseItemItem> {
        return apiService.getCourseItem(catId)
    }

}
