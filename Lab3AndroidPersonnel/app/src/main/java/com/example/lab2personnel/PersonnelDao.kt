package com.example.lab2personnel

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PersonnelDao {

    @Query("SELECT * FROM personnel")
    suspend fun getAll(): List<PersonnelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: PersonnelEntity)

    @Delete
    suspend fun delete(person: PersonnelEntity)

    @Query("DELETE FROM personnel")
    suspend fun clear()
}
