package com.example.lab2personnel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


// Стан UI для екрана
data class UiState(
    val type: PersonaType = PersonaType.ROBOCHYI,
    val name: String = "",
    val age: String = "",
    val hireDate: String = "",
    // Robochyi
    val shift: String = "денна",
    val hoursWorked: String = "",
    // Sluzhbovets
    val position: String = "",
    val salary: String = "",
    // Inzhener
    val specialization: String = "",
    val projectsCount: String = "",
    // Список працівників
    val people: List<Persona> = emptyList(),
    // Для відображення повідомлень
    val snackbarMessage: String? = null,
    val pendingDeleteIndex: Int? = null
)

// Репозиторій, який працює з Room-БД
class PersonnelRepository(
    private val dao: PersonnelDao
) {

    suspend fun getAll(): List<Persona> =
        dao.getAll().map { it.toPersona() }

    suspend fun add(persona: Persona) {
        dao.insert(persona.toEntity())
    }

    suspend fun removeAt(index: Int) {
        val current = dao.getAll()
        if (index in current.indices) {
            dao.delete(current[index])
        }
    }

    suspend fun clear() {
        dao.clear()
    }
}

class PersonnelViewModel(
    private val repo: PersonnelRepository
) : ViewModel() {

    private val _ui = MutableStateFlow(UiState())
    val ui: StateFlow<UiState> = _ui

    init {
        // При старті: якщо БД порожня — заповнюємо 30 тестовими записами
        viewModelScope.launch {
            val fromDb = repo.getAll()
            if (fromDb.isEmpty()) {
                val testData = generateTestData() // твоя функція з TestData.kt
                for (p in testData) {
                    repo.add(p)
                }
                val updated = repo.getAll()
                _ui.update { it.copy(people = updated) }
            } else {
                _ui.update { it.copy(people = fromDb) }
            }
        }
    }

    // --- Оновлення полів вводу ---

    fun setType(type: PersonaType) {
        _ui.update { it.copy(type = type) }
    }

    fun setName(value: String) {
        _ui.update { it.copy(name = value) }
    }

    fun setAge(value: String) {
        _ui.update { it.copy(age = value) }
    }

    fun setHireDate(value: String) {
        _ui.update { it.copy(hireDate = value) }
    }

    fun setShift(value: String) {
        _ui.update { it.copy(shift = value) }
    }

    fun setHoursWorked(value: String) {
        _ui.update { it.copy(hoursWorked = value) }
    }

    fun setPosition(value: String) {
        _ui.update { it.copy(position = value) }
    }

    fun setSalary(value: String) {
        _ui.update { it.copy(salary = value) }
    }

    fun setSpecialization(value: String) {
        _ui.update { it.copy(specialization = value) }
    }

    fun setProjectsCount(value: String) {
        _ui.update { it.copy(projectsCount = value) }
    }

    // --- Дії ---

    fun addPerson() {
        val state = _ui.value

        val name = state.name.trim()
        if (name.length < 2) {
            showError("Ім'я має містити щонайменше 2 символи")
            return
        }

        val ageInt = state.age.toIntOrNull()
        if (ageInt == null || ageInt !in 16..80) {
            showError("Вік має бути числом від 16 до 80")
            return
        }

        val hireDate = state.hireDate.ifBlank { null }

        val persona: Persona = when (state.type) {
            PersonaType.ROBOCHYI -> {
                val hours = state.hoursWorked.toIntOrNull()
                if (hours == null || hours !in 0..400) {
                    showError("Годин на місяць має бути від 0 до 400")
                    return
                }
                if (state.shift.isBlank()) {
                    showError("Зміна не може бути порожньою")
                    return
                }
                Robochyi(
                    name = name,
                    age = ageInt,
                    hireDate = hireDate,
                    shift = state.shift,
                    hoursWorked = hours
                )
            }

            PersonaType.SLUZHBOVETS -> {
                val pos = state.position.trim()
                if (pos.length < 2) {
                    showError("Посада має містити щонайменше 2 символи")
                    return
                }
                val salary = state.salary.replace(",", ".").toDoubleOrNull()
                if (salary == null || salary !in 0.0..1_000_000.0) {
                    showError("Зарплата має бути числом від 0 до 1 000 000")
                    return
                }
                Sluzhbovets(
                    name = name,
                    age = ageInt,
                    hireDate = hireDate,
                    position = pos,
                    salary = salary
                )
            }

            PersonaType.INZHENER -> {
                val spec = state.specialization.trim()
                if (spec.length < 2) {
                    showError("Спеціалізація має містити щонайменше 2 символи")
                    return
                }
                val projects = state.projectsCount.toIntOrNull()
                if (projects == null || projects !in 0..100) {
                    showError("Кількість проєктів має бути від 0 до 100")
                    return
                }
                Inzhener(
                    name = name,
                    age = ageInt,
                    hireDate = hireDate,
                    specialization = spec,
                    projectsCount = projects
                )
            }
        }

        viewModelScope.launch {
            repo.add(persona)
            val updated = repo.getAll()
            _ui.update {
                it.copy(
                    people = updated,
                    snackbarMessage = "Запис додано",
                    // очищаємо специфічні поля, але залишаємо обраний тип
                    age = "",
                    hireDate = "",
                    hoursWorked = "",
                    position = "",
                    salary = "",
                    specialization = "",
                    projectsCount = ""
                )
            }
        }
    }

    fun askDelete(index: Int) {
        _ui.update { it.copy(pendingDeleteIndex = index) }
    }

    fun confirmDelete() {
        val index = _ui.value.pendingDeleteIndex ?: return
        viewModelScope.launch {
            repo.removeAt(index)
            val updated = repo.getAll()
            _ui.update {
                it.copy(
                    people = updated,
                    snackbarMessage = "Запис видалено",
                    pendingDeleteIndex = null
                )
            }
        }
    }

    fun cancelDelete() {
        _ui.update { it.copy(pendingDeleteIndex = null) }
    }

    fun clearAll() {
        viewModelScope.launch {
            repo.clear()
            val updated = repo.getAll()
            _ui.update {
                it.copy(
                    people = updated,
                    snackbarMessage = "Список очищено"
                )
            }
        }
    }

    fun onSnackbarShown() {
        _ui.update { it.copy(snackbarMessage = null) }
    }

    private fun showError(message: String) {
        _ui.update { it.copy(snackbarMessage = message) }
    }
}
