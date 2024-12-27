package com.course.data.mappers

import com.course.domain.model.Incident
import com.course.data.model.IncidentDto
import com.course.domain.model.enums.Status
import com.course.domain.model.enums.IncidentType

fun IncidentDto.toDomainModel() = Incident(
    incidentId = incidentId,
    type = type,
    latitude = latitude,
    longitude = longitude,
    description = description,
    status = status,
    zoneId = zoneId
)

fun Incident.toDto() = IncidentDto(
    incidentId = incidentId,
    type = type,
    latitude = latitude,
    longitude = longitude,
    description = description,
    status = status,
    zoneId = zoneId
)



//fun IncidentDto.toDomainModel(): Incident {
//    return try {
//        Log.d("Mapper", "Mapping data: $incidentId")
//        Incident(
//            incidentId = incidentId,
//            type = incidentType?: incidentType,
//            latitude = latitude,
//            longitude = longitude,
//            description = description,
//            status = fromStatusToString(status),
//            zoneId = zoneId
//        )
//    } catch (e: Exception) {
//        Log.e("Mapper", "Error creating Incident ", e)
//        throw e // Перекидываем ошибку дальше после логирования
//
//    }
//}

fun fromStatusToString(status: Status): String {
    return when (status) {
        Status.NEW -> "New"
        Status.IN_PROGRESS -> "In progress"
        Status.CLOSED -> "Closed"
    }
}

    fun fromTypeToString(incidentType: IncidentType): String {
        return when (incidentType) {
            IncidentType.FIRE -> "Fire"
            IncidentType.FLOOD -> "Flood"
            IncidentType.NATURAL_DISASTER -> "Natural disaster"
            IncidentType.GASLEAK -> "Gas leak"
            IncidentType.OTHER -> "Other"
        }
    }


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
