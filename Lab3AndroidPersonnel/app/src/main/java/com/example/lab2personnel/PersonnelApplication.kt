package com.example.lab2personnel

import android.app.Application

class PersonnelApplication : Application() {

    // База даних
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }

    // Репозиторій
    val repository: PersonnelRepository by lazy {
        PersonnelRepository(database.personnelDao())
    }
}


