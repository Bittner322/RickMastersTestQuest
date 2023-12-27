package com.example.rickmasterstestquest.presentation.doors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmasterstestquest.data.database.models.Door
import com.example.rickmasterstestquest.data.repositories.DoorsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoorsViewModel @Inject constructor(
    private val doorsRepository: DoorsRepository
): ViewModel() {
    private val _doorsFlow = MutableStateFlow(emptyList<Door>())
    val doorsFlow = _doorsFlow.asStateFlow()

    private val _uiState = MutableStateFlow(DoorsScreenUiState.default)
    val uiState = _uiState.asStateFlow()

    private var gettingDoorsJob: Job? = null

    init {
        gettingDoorsJob = doorsRepository.getAllDoors()
            .onEach { _doorsFlow.value = it }
            .launchIn(viewModelScope)
        insertDoors()
    }

    fun onCameraFavoriteChanged(door: Door, isFavorite: Boolean) {
        viewModelScope.launch {
            doorsRepository.updateFavoriteDoor(door = door)
            _uiState.update {
                it.copy(
                    isFavorite = isFavorite
                )
            }
        }
    }

    private fun insertDoors() {
        viewModelScope.launch {
            doorsRepository.insertAllDoors()
        }
    }

    fun refreshDoors() {
        viewModelScope.launch {
            doorsRepository.refreshDoors()
        }
    }

    fun updateDoorName(id: Int, name: String) {
        viewModelScope.launch {
            doorsRepository.updateDoorName(
                id = id,
                name = name
            )
            _uiState.update {
                it.copy(
                    name = name
                )
            }
        }
    }
}