package com.example.seniorprojectdc.database

import com.example.seniorprojectdc.service_classes.Insect
import kotlinx.coroutines.flow.Flow

/*
This is a class that helps manage the database and makes it easier to use. It works with the DAO
to do this and new functions can be added easily
 */
class InsectRepository(private val dao: DAO) {
    val allInsects = dao.getAllInsects()

    fun getAllByDate(): Flow<List<Insect>> =
        dao.getAllByDate()

    fun getAllAlphabetical(): Flow<List<Insect>> =
        dao.getAllAlphabetical()

    suspend fun insert(insect: Insect) {
        dao.insert(insect)
    }

    suspend fun delete(insect: Insect) {
        dao.delete(insect)
    }

    suspend fun updateInsect(insect: Insect) {
        dao.updateInsect(insect)
    }
}