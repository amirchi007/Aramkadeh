package com.amir.aram.feature.courseActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amir.aram.model.dataClass.ChipItem
import com.amir.aram.model.dataClass.CourseItem
import com.amir.aram.model.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import retrofit2.Call
import javax.inject.Inject

@HiltViewModel
class CourseViewModel@Inject constructor(private val courseRepository: CourseRepository) : ViewModel() {

    private val _categoryItems = MutableLiveData<List<ChipItem.ChipItemItem>>()
    val categoryItems: LiveData<List<ChipItem.ChipItemItem>> get() = _categoryItems

    private val _courseItems = MutableLiveData<List<CourseItem.CourseItemItem>>()
    val courseItems: LiveData<List<CourseItem.CourseItemItem>> get() = _courseItems

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean> get() = _progressBar

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getCategoryItem() {
        _progressBar.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val items = courseRepository.getCategoryItem()
                _categoryItems.postValue(items)
            } catch (e: Exception) {
                _errorMessage.postValue(e.message ?: "Unknown error")
            } finally {
                _progressBar.postValue(false)
            }
        }
    }

    fun getCourseItem(catId: Int) {
        _progressBar.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val items = courseRepository.getCourseItem(catId)
                _courseItems.postValue(items)
            } catch (e: Exception) {
                _errorMessage.postValue(e.message ?: "Unknown error")
            } finally {
                _progressBar.postValue(false)
            }
        }
    }
}


