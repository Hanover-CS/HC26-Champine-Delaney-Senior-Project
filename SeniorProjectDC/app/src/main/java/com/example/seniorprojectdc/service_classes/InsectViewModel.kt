package com.example.seniorprojectdc.service_classes
/*
This is the viewModel. It lets us manage the database and move data from one screen to another
 */
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.seniorprojectdc.database.InsectRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getCurrentDateString(): String {
    val formatter = SimpleDateFormat( "yyyy-MM-dd HH:mm", Locale.getDefault())
    return formatter.format(Date())
}
class InsectViewModel( private val repository: InsectRepository) : ViewModel() {
    enum class SortMode { DATE, ALPHABETICAL }

    private val sortMode = MutableStateFlow( SortMode.DATE)
    val insects : StateFlow<List<Insect>> =
        sortMode.flatMapLatest { mode ->
            when (mode) {
                SortMode.DATE -> repository.getAllByDate()
                SortMode.ALPHABETICAL -> repository.getAllAlphabetical()
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList<Insect>()
        )

    fun addInsect( name: String, imageUri: Uri?, notes: String) {
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

    fun deleteInsect( insect: Insect) {
        viewModelScope.launch {
            repository.delete(insect)
        }
    }

    fun getInsectById( id: Int): Insect? {
        return insects.value.find { it.id == id }
    }

    fun updateInsect( insect: Insect) {
        viewModelScope.launch {
            repository.updateInsect(insect)
        }
    }

    fun setSortMode( mode: SortMode) {
        sortMode.value = mode
    }
}

class InsectViewModelFactory( private val repository: InsectRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InsectViewModel(repository) as T
    }
}
