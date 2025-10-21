package com.example.seniorprojectdc

class InsectRepository(private val dao: DAO) {
    val allInsects = dao.getAllInsects()

    suspend fun insert(score: Insect) {
        dao.insert(score)
    }

    suspend fun delete(score: Insect) {
        dao.delete(score)
    }
}
