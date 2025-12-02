# Лабораторна робота 3 — Збереження персоналу (Room + Jetpack Compose)

## Мета роботи
Розробити застосунок для обліку персоналу з використанням:
- Jetpack Compose для UI
- Room Database для збереження даних
- MVVM (ViewModel)
- Material Design 3

---

## Загальний опис
Застосунок дозволяє:
- додавати працівників 3‑х типів: робітник, службовець, інженер;
- вводити індивідуальні атрибути кожного типу;
- переглядати список працівників;
- видаляти вибраного працівника;
- очищати весь список;
- зберігати дані між перезапусками (Room DB).

---

## Хід роботи

### 1. Створення моделей даних

### `Models.kt`
```kotlin
sealed class Persona(
    open val name: String,
    open val age: Int,
    open val hireDate: String?
) {
    abstract fun printInfo(): String
    abstract fun doWork(): String
}

data class Robochyi(
    override val name: String,
    override val age: Int,
    override val hireDate: String?,
    val shift: String,
    val hoursWorked: Int
) : Persona(name, age, hireDate)

data class Sluzhbovets(
    override val name: String,
    override val age: Int,
    override val hireDate: String?,
    val position: String,
    val salary: Double
) : Persona(name, age, hireDate)

data class Inzhener(
    override val name: String,
    override val age: Int,
    override val hireDate: String?,
    val specialization: String,
    val projectsCount: Int
) : Persona(name, age, hireDate)
```

---

### 2. Enum PersonaType
```kotlin
enum class PersonaType {
    ROBOCHYI, SLUZHBOVETS, INZHENER
}
```

---

### 3. Entity + Mapping

### `PersonnelEntity.kt`
```kotlin
@Entity(tableName = "personnel")
data class PersonnelEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: PersonaType,
    val name: String,
    val age: Int,
    val hireDate: String?,
    val shift: String?,
    val hoursWorked: Int?,
    val position: String?,
    val salary: Double?,
    val specialization: String?,
    val projectsCount: Int?
)
```

---

### 4. DAO
```kotlin
@Dao
interface PersonnelDao {
    @Query("SELECT * FROM personnel")
    fun getAll(): Flow<List<PersonnelEntity>>

    @Insert
    suspend fun insert(person: PersonnelEntity)

    @Query("DELETE FROM personnel")
    suspend fun clearAll()

    @Delete
    suspend fun delete(person: PersonnelEntity)
}
```

---

### 5. RoomDatabase
```kotlin
@Database(entities = [PersonnelEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personnelDao(): PersonnelDao
}
```

---

### 6. ViewModel
```kotlin
class PersonnelViewModel(
    private val repository: PersonnelRepository
) : ViewModel() {

    private val _ui = MutableStateFlow(UiState())
    val ui = _ui.asStateFlow()

    fun addPerson() { ... }
    fun confirmDelete() { ... }
    fun clearAll() { ... }
}
```

---

### 7. UI — MainActivity & Compose

Фрагмент:
```kotlin
Scaffold(
    topBar = { TopAppBar(title = { Text("ЛР3 — Персонал") }) },
    floatingActionButton = {
        FloatingActionButton(onClick = { vm.addPerson() }) { Text("+") }
    }
) { padding ->
    AppContent(
        ui = ui,
        onTypeChange = vm::setType,
        onNameChange = vm::setName,
        ...
    )
}
```

---

### 8. DatePickerField
```kotlin
@Composable
fun DatePickerField(label: String, dateText: String, onDateSelected: (String) -> Unit) {
    var openDialog by remember { mutableStateOf(false) }
    val state = rememberDatePickerState()
    ...
}
```

---

## Висновок
Застосунок повністю реалізує CRUD логіку для роботи з персоналом, застосовує MVVM, Compose та Room. Дані зберігаються у локальній БД, інтерфейс динамічно адаптується під тип працівника.

