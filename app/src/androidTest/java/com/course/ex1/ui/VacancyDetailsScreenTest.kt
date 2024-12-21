package com.course.ex1.ui

//import androidx.compose.ui.test.assertIsDisplayed
//import androidx.compose.ui.test.assertIsEnabled
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick

import androidx.navigation.compose.rememberNavController
import com.course.domain.model.Company
import com.course.domain.model.CompanyDetails
import com.course.domain.repository.Repository
import com.course.ex1.viewmodel.VacancyDetailsViewModel

import org.junit.Rule
import org.junit.Test


class VacancyDetailsScreenTest {


    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testVacancyDetailsScreen() {
        // Создание поддельных UseCase с использованием FakeRepository
        val fakeRepository = FakeRepository()
        val getFakeVacancyDetailsUseCase = GetVacancyDetailsUseCase(fakeRepository)
        val getFakeCompaniesUseCase = GetCompaniesUseCase(fakeRepository)

        composeTestRule.setContent {
            VacancyDetailsScreen(
                navController = rememberNavController(),
                vacancyId = 1,
                viewModel = VacancyDetailsViewModel(
                    getFakeVacancyDetailsUseCase,
                    getFakeCompaniesUseCase
                )
            )
        }

        composeTestRule.onNodeWithText("Разработчик").assertIsDisplayed()
        composeTestRule.onNodeWithText("Уровень кандидата:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Senior").assertIsDisplayed()
        composeTestRule.onNodeWithText("Уровень зарплаты:").assertIsDisplayed()
        composeTestRule.onNodeWithText("1000000").assertIsDisplayed()
        composeTestRule.onNodeWithText("Описание:").assertIsDisplayed()
        composeTestRule.onNodeWithText("Делать приложения на Android").assertIsDisplayed()

        // Проверка кнопки компании
        composeTestRule.onNodeWithText("Школа 21")
            .assertIsDisplayed()
            .assertIsEnabled()
           // .performClick()
    }

}

class FakeRepository : Repository {
    override suspend fun getCompanies(): List<Company> {
        return listOf(Company(companyId = 1, name = "Школа 21", fieldOfActivity = "Образование"))
    }

    override suspend fun getCompanyDetails(id: Int): CompanyDetails {
        TODO("Not yet implemented")
    }

    override suspend fun getVacancies(): List<Vacancy> {
        TODO("Not yet implemented")
    }


    override suspend fun getVacancyDetails(vacancyId: Int): Vacancy {
        return Vacancy(
            vacancyId = 1,
            profession = "Разработчик",
            level = "Senior",
            salary = "1000000",
            description = "Делать приложения на Android",
            companyName = "Школа 21"
        )
    }
}