package com.example.lab2personnel

// Базовий sealed-клас, щоб when по Persona був "exhaustive"
sealed class Persona(
    open val name: String,
    open val age: Int,
    open val hireDate: String?      // yyyy-MM-dd або null
) {
    abstract fun printInfo(): String
    abstract fun doWork(): String
}

// ---------- Робітник ----------
data class Robochyi(
    override val name: String,
    override val age: Int,
    override val hireDate: String?,
    val shift: String,              // "денна"/"нічна"
    val hoursWorked: Int            // годин на місяць
) : Persona(name, age, hireDate) {

    override fun printInfo(): String =
        "Робітник $name, $age років, прийнятий: ${hireDate ?: "-"}, " +
                "зміна: $shift, $hoursWorked год/міс"

    override fun doWork(): String =
        "Виконує фізичну роботу у $shift зміні."
}

// ---------- Службовець ----------
data class Sluzhbovets(
    override val name: String,
    override val age: Int,
    override val hireDate: String?,
    val position: String,
    val salary: Double
) : Persona(name, age, hireDate) {

    override fun printInfo(): String =
        "Службовець $name, $age років, посада: $position, " +
                "зарплата: $salary грн, прийнятий: ${hireDate ?: "-"}"

    override fun doWork(): String =
        "Виконує офісну / адміністративну роботу на посаді $position."
}

// ---------- Інженер ----------
data class Inzhener(
    override val name: String,
    override val age: Int,
    override val hireDate: String?,
    val specialization: String,
    val projectsCount: Int
) : Persona(name, age, hireDate) {

    override fun printInfo(): String =
        "Інженер $name, $age років, спец.: $specialization, " +
                "проєктів: $projectsCount, прийнятий: ${hireDate ?: "-"}"

    override fun doWork(): String =
        "Працює інженером за спеціалізацією $specialization."
}
