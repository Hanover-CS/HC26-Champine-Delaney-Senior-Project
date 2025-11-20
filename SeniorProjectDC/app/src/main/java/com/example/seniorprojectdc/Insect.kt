package com.example.seniorprojectdc
import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

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
