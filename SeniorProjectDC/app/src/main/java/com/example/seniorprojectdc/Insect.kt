package com.example.seniorprojectdc
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/*
This class defines the insect objects to be stored in the database and tells the database
how to store them. here we can add or remove fields to the insect object.
 */
@Parcelize
@Entity(tableName = "insects")
data class Insect(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var insectName: String,
    val date: String,
    val imageUri: String?,
    var notes: String,
    var nickname: String,

) : Parcelable
