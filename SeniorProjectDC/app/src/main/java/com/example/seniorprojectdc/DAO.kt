package com.example.seniorprojectdc

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(insect: Insect)

    @Query("SELECT * FROM insects ORDER BY date DESC")
    fun getAllInsects(): Flow<List<Insect>>

    @Delete
    suspend fun delete(insect: Insect)
}