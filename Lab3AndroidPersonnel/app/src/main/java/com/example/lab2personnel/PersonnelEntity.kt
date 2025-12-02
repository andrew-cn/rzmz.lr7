package com.example.lab2personnel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personnel")
data class PersonnelEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: PersonaType,
    val name: String,
    val age: Int,
    val hireDate: String?,          // зберігаємо як текст

    // Поля специфічні для типів
    val shift: String?,             // Robochyi
    val hoursWorked: Int?,          // Robochyi

    val position: String?,          // Sluzhbovets
    val salary: Double?,            // Sluzhbovets

    val specialization: String?,    // Inzhener
    val projectsCount: Int?         // Inzhener
)

// ------------ Мапінг Entity -> Persona ------------

fun PersonnelEntity.toPersona(): Persona = when (type) {
    PersonaType.ROBOCHYI -> Robochyi(
        name = name,
        age = age,
        hireDate = hireDate,
        shift = shift ?: "",
        hoursWorked = hoursWorked ?: 0
    )
    PersonaType.SLUZHBOVETS -> Sluzhbovets(
        name = name,
        age = age,
        hireDate = hireDate,
        position = position ?: "",
        salary = salary ?: 0.0
    )
    PersonaType.INZHENER -> Inzhener(
        name = name,
        age = age,
        hireDate = hireDate,
        specialization = specialization ?: "",
        projectsCount = projectsCount ?: 0
    )
}

// ------------ Мапінг Persona -> Entity ------------

fun Persona.toEntity(): PersonnelEntity = when (this) {
    is Robochyi -> PersonnelEntity(
        type = PersonaType.ROBOCHYI,
        name = name,
        age = age,
        hireDate = hireDate,
        shift = shift,
        hoursWorked = hoursWorked,
        position = null,
        salary = null,
        specialization = null,
        projectsCount = null
    )
    is Sluzhbovets -> PersonnelEntity(
        type = PersonaType.SLUZHBOVETS,
        name = name,
        age = age,
        hireDate = hireDate,
        shift = null,
        hoursWorked = null,
        position = position,
        salary = salary,
        specialization = null,
        projectsCount = null
    )
    is Inzhener -> PersonnelEntity(
        type = PersonaType.INZHENER,
        name = name,
        age = age,
        hireDate = hireDate,
        shift = null,
        hoursWorked = null,
        position = null,
        salary = null,
        specialization = specialization,
        projectsCount = projectsCount
    )
}
