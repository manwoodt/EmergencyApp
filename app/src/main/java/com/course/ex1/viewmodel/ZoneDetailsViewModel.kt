package com.course.ex1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.course.domain.model.Zone
import com.course.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class ZoneDetailsViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    private val _zone = MutableLiveData<Zone>()
    val zone: LiveData<Zone> get() = _zone

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    fun loadZoneDetails(zoneId: Long) {
        viewModelScope.launch {
            try {
                val zoneDetails = repository.getZonesById(zoneId)
                _zone.postValue(zoneDetails)
            } catch (e: SocketTimeoutException) {
                _errorMessage.value = "Server response timed out. Please try again."
            } catch (e: Exception) {
                _errorMessage.value = "An error occurred: ${e.message}"
            }

        }
    }


}