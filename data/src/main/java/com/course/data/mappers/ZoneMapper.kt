package com.course.data.mappers

import com.course.domain.model.Zone
import com.course.data.model.ZoneDto
import com.course.domain.model.enums.Level


fun ZoneDto.toDomainModel() = Zone(
    zoneId = zoneId,
    name = name,
    phone = phone,
    minLatitude = minLatitude,
    maxLatitude = maxLatitude,
    minLongitude = minLongitude,
    maxLongitude = maxLongitude,
    level = toColor(level)

)

fun toColor(level: Level): Int {
    return when (level) {
        Level.LOW -> 0xFF00FF00.toInt() // Зеленый цвет
        Level.MEDIUM -> 0xFFFFFF00.toInt() // Желтый цвет
        Level.HIGH -> 0xFFFF0000.toInt() // Красный цвет
    }
}
