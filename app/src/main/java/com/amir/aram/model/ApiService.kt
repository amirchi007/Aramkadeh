package com.amir.aram.model

import com.amir.aram.model.dataClass.ChipItem
import com.amir.aram.model.dataClass.CourseItem
import com.amir.aram.model.dataClass.SessionItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("meditation_cat.php")
    suspend fun getCategoryItem() : List<ChipItem.ChipItemItem>

    @GET("meditation_item.php")
    suspend fun getCourseItem(
        @Query("cat_id") catId: Int
    ): List<CourseItem.CourseItemItem>

    @GET("meditation_session.php")
    suspend fun getSessionItem(
        @Query("item_id") itemId: Int
    ):List<SessionItem.SessionItem>
}