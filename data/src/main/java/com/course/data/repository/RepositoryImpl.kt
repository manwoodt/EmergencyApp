package com.course.data.repository


import com.course.data.mappers.toDomainModel
import com.course.data.mappers.toDto
import com.course.data.model.IncidentDto
import com.course.data.network.ApiService
import com.course.domain.model.Incident
import com.course.domain.model.Zone
import com.course.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : Repository {

//    override suspend fun getCompanies(): List<Company> {
//        return apiService.getCompanies().map { it.toDomainModel() }
//    }
//
//    override suspend fun getCompanyDetails(id: Int): CompanyDetails {
//        val vacancies = apiService.getVacancies()
//        val companyDetails = apiService.getCompanyDetails(id).toDomainModel()
//        // println("Детали компаний $companyDetails")
//        return companyDetails
//    }
//
//    override suspend fun getVacancies(): List<Vacancy> {
//
//        val companies = apiService.getCompanies()
//        val vacancies = mutableListOf<Vacancy>()
//
//        for (company in companies) {
//            val companyDetails = getCompanyDetails(company.companyId)
//            vacancies.addAll(
//                companyDetails.vacancies.map { vacancy ->
//                    vacancy.copy(companyName = companyDetails.name) // Добавляем имя компании в вакансию
//                }
//            )
//        }
//        return vacancies
//    }
//
//    override suspend fun getVacancyDetails(id: Int): Vacancy {
//        val vacancyWithoutCompanyName = apiService.getVacancyDetails(id).toDomainModel()
//        val vacancies = getVacancies()
//        if (id != 0)
//            vacancyWithoutCompanyName.companyName = vacancies[id - 1].companyName
//        return vacancyWithoutCompanyName
//    }

    override suspend fun getZonesList(): List<Zone> {
        return apiService.getZonesList().map { it.toDomainModel() }
    }

    override suspend fun getZonesById(zoneId: Long): Zone {
        return apiService.getZonesById(zoneId).toDomainModel()
    }

    override suspend fun getIncidentsList(): List<Incident> {
        return apiService.getIncidentsList().map { it.toDomainModel() }
    }

    override suspend fun getIncidentsByZone(zoneId: Long): List<Incident> {
        return apiService.getIncidentsByZone(zoneId).map { it.toDomainModel() }
    }

    override suspend fun createIncident(incident: Incident) {
        return apiService.createIncident(incident.toDto())
    }

    override suspend fun updateIncident(incident: Incident) {
        throw NotImplementedError("Not yet implemented")
    }

    override suspend fun calculateZoneByCoordinates(latitude: Double, longitude: Double): Long {
        throw NotImplementedError("Not yet implemented")
    }

}


