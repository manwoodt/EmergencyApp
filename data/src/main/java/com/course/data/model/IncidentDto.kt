package com.course.data.model

import com.course.domain.model.UNDEFINED_ZONE_ID
import com.course.domain.model.enums.Status
import com.course.domain.model.enums.IncidentType
import kotlinx.serialization.Serializable


@Serializable
data class IncidentDto(
    val incidentId: Long? = null,
    val type: IncidentType,
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val status: Status,
    var zoneId: Long? = UNDEFINED_ZONE_ID
)
