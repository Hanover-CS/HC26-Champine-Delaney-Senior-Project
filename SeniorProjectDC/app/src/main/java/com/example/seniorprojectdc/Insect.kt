package com.example.seniorprojectdc
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "insects")
data class Insect(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val insectName: String,
    val date: Int
)
