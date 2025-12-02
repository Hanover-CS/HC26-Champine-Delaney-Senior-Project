package com.example.seniorprojectdc.DB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.seniorprojectdc.Insect
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(insect: Insect)

    @Query("SELECT * FROM insects ORDER BY date DESC")
    fun getAllInsects(): Flow<List<Insect>>

    @Delete
    suspend fun delete(insect: Insect)

    @Update
    suspend fun updateInsect(insect: Insect)
}