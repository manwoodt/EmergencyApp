package com.course.ex1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.course.domain.model.Zone
import com.course.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class ZonesViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _zones = MutableLiveData<List<Zone>>()
    val zones: LiveData<List<Zone>> = _zones

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    init {
        loadZones()
    }


    private fun loadZones() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getZonesList()
                _zones.postValue(result)
            } catch (e: SocketTimeoutException) {
                _errorMessage.value = "Server response timed out. Please try again."
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
            } finally {
                _isLoading.value = false
            }

        }
    }


}