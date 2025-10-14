package com.example.seniorprojectdc
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class InsectViewModel(private val repository: InsectRepository) : ViewModel() {
    val insects = repository.allInsects.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    fun addInsect(name: String, points: Int) {
        viewModelScope.launch {
            repository.insert(Insect(insectName = name, date = points))
        }
    }

    fun deleteInsect(score: Insect) {
        viewModelScope.launch {
            repository.delete(score)
        }
    }
}

class InsectViewModelFactory(private val repository: InsectRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InsectViewModel(repository) as T
    }
}
