package com.example.lab2personnel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [PersonnelEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(PersonaTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personnelDao(): PersonnelDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "personnel_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}

