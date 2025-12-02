package com.example.lab2personnel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PersonnelViewModelFactory(
    private val repository: PersonnelRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PersonnelViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PersonnelViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
