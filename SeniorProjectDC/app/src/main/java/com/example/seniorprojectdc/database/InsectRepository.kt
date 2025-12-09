package com.example.seniorprojectdc.database

import com.example.seniorprojectdc.service_classes.Insect

/*
This is a class that helps manage the database and makes it easier to use. It works with the DAO
to do this and new functions can be added easily
 */
class InsectRepository(private val dao: DAO) {
    val allInsects = dao.getAllInsects()

    suspend fun insert(score: Insect) {
        dao.insert(score)
    }

    suspend fun delete(score: Insect) {
        dao.delete(score)
    }

    suspend fun updateInsect(insect: Insect) {
        dao.updateInsect(insect)
    }
}