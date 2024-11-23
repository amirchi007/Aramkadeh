package com.amir.aram.model.repository

import com.amir.aram.model.ApiService
import com.amir.aram.model.dataClass.SessionItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getSessionItem(itemId: Int): List<SessionItem.SessionItem> {
        return apiService.getSessionItem(itemId)
    }
}
