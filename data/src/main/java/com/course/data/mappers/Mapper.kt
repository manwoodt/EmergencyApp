package com.course.data.mappers

import com.course.domain.model.Incident
import com.course.domain.model.Zone
import com.example.data.network.dto.IncidentDto
import com.example.data.network.dto.ZoneDto

fun Zone.toZoneDto() = ZoneDto(
    zoneId = zoneId,
    name = name,
    phone = phone,
    minLatitude = minLatitude,
    maxLatitude = maxLatitude,
    minLongitude = minLongitude,
    maxLongitude = maxLongitude,
    level = level
)

fun Incident.toIncidentDto() = IncidentDto(
    incidentId = incidentId,
    type = type,
    latitude = latitude,
    longitude = longitude,
    description = description,
    status = status,
    zoneId = zoneId
)

//fun ResultRow.toZone(): Zone = Zone(
//    zoneId = this[ZonesTable.zoneId],
//    name = this[ZonesTable.name],
//    phone = this[ZonesTable.phone],
//    minLatitude = this[ZonesTable.minLatitude],
//    maxLatitude = this[ZonesTable.maxLatitude],
//    minLongitude = this[ZonesTable.minLongitude],
//    maxLongitude = this[ZonesTable.maxLongitude],
//    level = this[ZonesTable.level]
//)
//
//fun List<ResultRow>.toZonesList(): List<Zone> = this.map { it.toZone() }
//
//fun ResultRow.toIncident(): Incident = Incident(
//    incidentId = this[IncidentsTable.incidentId],
//    type = this[IncidentsTable.type],
//    latitude = this[IncidentsTable.latitude],
//    longitude = this[IncidentsTable.longitude],
//    description = this[IncidentsTable.description],
//    status = this[IncidentsTable.status],
//    zoneId = this[IncidentsTable.zoneId]
//)
//
//fun List<ResultRow>.toIncidentsList(): List<Incident> = this.map { it.toIncident() }
