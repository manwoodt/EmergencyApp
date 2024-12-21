package com.example.data.network.dto


import com.course.domain.model.enums.Level
import kotlinx.serialization.Serializable

@Serializable
data class ZoneDto(
    val zoneId: Long,
    val name: String,
    val phone: String,
    val minLatitude: Double,
    val maxLatitude: Double,
    val minLongitude: Double,
    val maxLongitude: Double,
    val level: Level
)