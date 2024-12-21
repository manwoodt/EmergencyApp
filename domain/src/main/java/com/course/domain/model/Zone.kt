package com.course.domain.model

import com.course.domain.model.enums.Level


data class Zone(
    val zoneId: Long,
    val name: String,
    val phone: String,
    val minLatitude: Double,
    val maxLatitude: Double,
    val minLongitude: Double,
    val maxLongitude: Double,
    val level: Level,
)