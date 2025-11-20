package com.example.seniorprojectdc
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentDateString(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    return formatter.format(Date())
}
class InsectViewModel(private val repository: InsectRepository) : ViewModel() {
    val insects = repository.allInsects.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    fun addInsect(name: String, imageUri: Uri?, notes: String) {
        viewModelScope.launch {
            repository.insert(
                Insect(
                insectName = name,
                date = getCurrentDateString(),
                imageUri = imageUri?.toString(),
                    notes = notes,
                    nickname = ""
            )
            )
        }
    }

    fun deleteInsect(insect: Insect) {
        viewModelScope.launch {
            repository.delete(insect)
        }
    }

    fun getInsectById(id: Int): Insect? {
        return insects.value.find { it.id == id }
    }

    fun updateInsect(insect: Insect) {
        viewModelScope.launch {
            repository.updateInsect(insect)
        }
    }
}

class InsectViewModelFactory(private val repository: InsectRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InsectViewModel(repository) as T
    }
}
