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
import javax.inject.Inject

@HiltViewModel
class ZonesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _zones = MutableLiveData<List<Zone>>()
    val zones: LiveData<List<Zone>> = _zones

    init {
        loadZones()
    }

    private fun loadZones() {
        viewModelScope.launch {
            val result = repository.getZonesList()
            _zones.postValue(result)
        }
    }

}