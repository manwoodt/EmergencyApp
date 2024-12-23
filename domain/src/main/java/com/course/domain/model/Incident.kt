package com.course.domain.model

import com.course.domain.model.enums.Status
import com.course.domain.model.enums.Type


const val UNDEFINED_ZONE_ID = 0L

data class Incident(
    val incidentId: Long? = null,
    val type: String,
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val status: String,
    val zoneId: Long? = UNDEFINED_ZONE_ID
)
