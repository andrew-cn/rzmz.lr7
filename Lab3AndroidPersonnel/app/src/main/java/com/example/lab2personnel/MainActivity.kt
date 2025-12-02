package com.example.lab2personnel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Calendar

class MainActivity : ComponentActivity() {

    private val vm: PersonnelViewModel by viewModels {
        val app = application as PersonnelApplication
        PersonnelViewModelFactory(app.repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PersonnelScreen(vm)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonnelScreen(vm: PersonnelViewModel) {
    val ui by vm.ui.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(ui.snackbarMessage) {
        ui.snackbarMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            vm.onSnackbarShown()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ЛР3 — Персонал (Room + Compose)") }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = { vm.addPerson() }) {
                Text("+")
            }
        }
    ) { padding ->
        AppContent(
            ui = ui,
            onTypeChange = vm::setType,
            onNameChange = vm::setName,
            onAgeChange = vm::setAge,
            onHireDateChange = vm::setHireDate,
            onShiftChange = vm::setShift,
            onHoursChange = vm::setHoursWorked,
            onPositionChange = vm::setPosition,
            onSalaryChange = vm::setSalary,
            onSpecChange = vm::setSpecialization,
            onProjectsChange = vm::setProjectsCount,
            onAskDelete = vm::askDelete,
            onConfirmDelete = vm::confirmDelete,
            onCancelDelete = vm::cancelDelete,
            onClearAll = vm::clearAll,
            modifier = Modifier.padding(padding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(
    ui: UiState,
    onTypeChange: (PersonaType) -> Unit,
    onNameChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onHireDateChange: (String) -> Unit,
    onShiftChange: (String) -> Unit,
    onHoursChange: (String) -> Unit,
    onPositionChange: (String) -> Unit,
    onSalaryChange: (String) -> Unit,
    onSpecChange: (String) -> Unit,
    onProjectsChange: (String) -> Unit,
    onAskDelete: (Int) -> Unit,
    onConfirmDelete: () -> Unit,
    onCancelDelete: () -> Unit,
    onClearAll: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HeaderTitle()

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Дані працівника",
                style = MaterialTheme.typography.titleMedium
            )
            HelpIcon()
        }

        Spacer(modifier = Modifier.height(8.dp))

        // вибір типу працівника
        PersonaTypeSelector(
            current = ui.type,
            onTypeChange = onTypeChange
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = ui.name,
            onValueChange = onNameChange,
            label = { Text("ПІБ") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = ui.age,
            onValueChange = onAgeChange,
            label = { Text("Вік") },
            modifier = Modifier.fillMaxWidth()
        )

        DatePickerField(
            label = "Дата прийняття на роботу",
            dateText = ui.hireDate,
            onDateSelected = onHireDateChange
        )

        when (ui.type) {
            PersonaType.ROBOCHYI -> {
                ShiftField(ui.shift, onShiftChange)
                OutlinedTextField(
                    value = ui.hoursWorked,
                    onValueChange = onHoursChange,
                    label = { Text("Годин/місяць") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            PersonaType.SLUZHBOVETS -> {
                OutlinedTextField(
                    value = ui.position,
                    onValueChange = onPositionChange,
                    label = { Text("Посада") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = ui.salary,
                    onValueChange = onSalaryChange,
                    label = { Text("Зарплата, грн") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            PersonaType.INZHENER -> {
                OutlinedTextField(
                    value = ui.specialization,
                    onValueChange = onSpecChange,
                    label = { Text("Спеціалізація") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = ui.projectsCount,
                    onValueChange = onProjectsChange,
                    label = { Text("К-сть проєктів") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        OutlinedButton(
            onClick = onClearAll,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Очистити список")
        }

        Text(
            text = "Список персоналу:",
            style = MaterialTheme.typography.titleMedium
        )

        PersonList(
            people = ui.people,
            onAskDelete = onAskDelete
        )

        if (ui.pendingDeleteIndex != null) {
            AlertDialog(
                onDismissRequest = onCancelDelete,
                confirmButton = {
                    TextButton(onClick = onConfirmDelete) {
                        Text("Так")
                    }
                },
                dismissButton = {
                    TextButton(onClick = onCancelDelete) {
                        Text("Ні")
                    }
                },
                title = { Text("Підтвердження") },
                text = { Text("Видалити цього працівника?") }
            )
        }
    }
}

@Composable
fun HeaderTitle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Персонал компанії", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun HelpIcon() {
    Text(
        text = "ⓘ",
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun PersonaTypeSelector(
    current: PersonaType,
    onTypeChange: (PersonaType) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TypeOption("Робітник",    PersonaType.ROBOCHYI,    current, onTypeChange)
        TypeOption("Службовець", PersonaType.SLUZHBOVETS, current, onTypeChange)
        TypeOption("Інженер",    PersonaType.INZHENER,    current, onTypeChange)
    }
}

@Composable
fun TypeOption(
    label: String,
    type: PersonaType,
    current: PersonaType,
    onTypeChange: (PersonaType) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        RadioButton(
            selected = current == type,
            onClick = { onTypeChange(type) }
        )
        Text(label)
    }
}

@Composable
fun ShiftField(current: String, onShiftChange: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Зміна:")
        ShiftOption("денна", current, onShiftChange)
        ShiftOption("нічна", current, onShiftChange)
    }
}

@Composable
fun ShiftOption(label: String, current: String, onShiftChange: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        RadioButton(
            selected = current == label,
            onClick = { onShiftChange(label) }
        )
        Text(label)
    }
}

@Composable
fun PersonList(
    people: List<Persona>,
    onAskDelete: (Int) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(people) { index, person ->
            PersonItem(
                person = person,
                onDeleteClick = { onAskDelete(index) }
            )
        }
    }
}

@Composable
fun PersonItem(
    person: Persona,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(person.printInfo(), style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(person.doWork(), style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = onDeleteClick) {
                    Text("Видалити")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerField(
    label: String,
    dateText: String,
    onDateSelected: (String) -> Unit
) {
    var openDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    OutlinedTextField(
        value = dateText,
        onValueChange = {},
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { openDialog = true },
        readOnly = true,
        enabled = false
    )

    if (openDialog) {
        DatePickerDialog(
            onDismissRequest = { openDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val millis = datePickerState.selectedDateMillis
                        if (millis != null) {
                            val cal = Calendar.getInstance().apply { timeInMillis = millis }
                            val year = cal.get(Calendar.YEAR)
                            val month = cal.get(Calendar.MONTH) + 1
                            val day = cal.get(Calendar.DAY_OF_MONTH)
                            onDateSelected("%04d-%02d-%02d".format(year, month, day))
                        }
                        openDialog = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { openDialog = false }) {
                    Text("Скасувати")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
