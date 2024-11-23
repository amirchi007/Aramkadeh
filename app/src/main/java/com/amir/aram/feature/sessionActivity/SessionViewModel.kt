package com.amir.aram.feature.sessionActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amir.aram.model.dataClass.ChipItem
import com.amir.aram.model.dataClass.CourseItem
import com.amir.aram.model.dataClass.SessionItem
import com.amir.aram.model.repository.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel@Inject constructor(private val sessionRepository: SessionRepository) : ViewModel() {

    private val _sessionItems = MutableLiveData<List<SessionItem.SessionItem>>()
    val sessionItems: LiveData<List<SessionItem.SessionItem>> get() = _sessionItems

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getSessionItem(itemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val items = sessionRepository.getSessionItem(itemId)
                _sessionItems.postValue(items)
            } catch (e: Exception) {
                _errorMessage.postValue(e.message ?: "Unknown error")
            }
        }
    }
}
