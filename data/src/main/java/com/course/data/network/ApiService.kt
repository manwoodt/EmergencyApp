package com.course.data.network


import com.course.data.model.IncidentDto
import com.course.data.model.ZoneDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("zones")
    suspend fun getZonesList(): List<ZoneDto>

    @GET("zones/{zoneId}")
    suspend fun getZonesById(@Path("zoneId") zoneId: Long): ZoneDto

    @GET("incidents")
    suspend fun getIncidentsList(): List<IncidentDto>

    @GET("incidents/{zoneId}")
    suspend fun getIncidentsByZone(@Path("zoneId") zoneId: Long): List<IncidentDto>

    @POST("create")
    suspend fun createIncident(incident: IncidentDto)

    @PUT("update")
    suspend fun updateIncident(incident: IncidentDto)

    fun calculateZone(incident: IncidentDto): Long

}
