package com.example.lab2personnel

import androidx.room.TypeConverter

class PersonaTypeConverters {

    @TypeConverter
    fun fromPersonaType(type: PersonaType): String = type.name

    @TypeConverter
    fun toPersonaType(value: String): PersonaType =
        PersonaType.valueOf(value)
}
