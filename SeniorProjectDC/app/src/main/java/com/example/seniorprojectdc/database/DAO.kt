package com.example.seniorprojectdc.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.seniorprojectdc.service_classes.Insect
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(insect: Insect)

    @Query(
        "SELECT * FROM insects " +
            "WHERE insectName LIKE '%' || :query || '%' " +
            "OR nickname LIKE '%' || :query || '%'"
    )
    fun searchByName(query: String): Flow<List<Insect>>

    @Query("SELECT * FROM insects ORDER BY date DESC")
    fun getAllByDate(): Flow<List<Insect>>

    @Query("SELECT * FROM insects ORDER BY insectName COLLATE NOCASE ASC")
    fun getAllAlphabetical(): Flow<List<Insect>>
    @Query("SELECT * FROM insects ORDER BY date DESC")
    fun getAllInsects(): Flow<List<Insect>>

    @Delete
    suspend fun delete(insect: Insect)

    @Update
    suspend fun updateInsect(insect: Insect)
}