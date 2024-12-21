package com.course.domain.repository

import com.course.domain.model.Incident
import com.course.domain.model.Zone

interface Repository {

   suspend fun getZonesList(): List<Zone>

    suspend fun getIncidentsList(): List<Incident>

    suspend fun getIncidentsByZone(zoneId: Long): List<Incident>

    //IncidentDto??
    suspend fun createIncident(incident: Incident)

    suspend fun updateIncident(incident: Incident)

    suspend fun calculateZoneByCoordinates(latitude: Double, longitude: Double): Long
}
