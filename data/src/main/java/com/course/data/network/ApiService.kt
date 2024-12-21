package com.course.data.network


import com.example.data.network.dto.IncidentDto
import com.example.data.network.dto.ZoneDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("zones")
    suspend fun getZonesList(): List<ZoneDto>

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
