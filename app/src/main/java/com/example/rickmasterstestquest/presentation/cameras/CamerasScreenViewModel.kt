package com.example.rickmasterstestquest.presentation.cameras

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmasterstestquest.data.database.models.Camera
import com.example.rickmasterstestquest.data.repositories.CamerasRepository
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
class CamerasScreenViewModel @Inject constructor(
    private val camerasRepository: CamerasRepository
): ViewModel() {
    private val _camerasFlow = MutableStateFlow(emptyList<Camera>())
    val camerasFlow = _camerasFlow.asStateFlow()

    private val _uiState = MutableStateFlow(CamerasScreenUiState.default)
    val uiState = _uiState.asStateFlow()

    private var gettingCamerasJob: Job? = null

    init {
        gettingCamerasJob = camerasRepository.getAllCameras()
            .onEach { _camerasFlow.value = it }
            .launchIn(viewModelScope)
        insertCameras()
    }

    fun onCameraFavoriteChanged(camera: Camera, isFavorite: Boolean) {
        viewModelScope.launch {
            camerasRepository.updateFavoriteCamera(camera = camera)
            _uiState.update {
                it.copy(
                    isFavorite = isFavorite
                )
            }
        }
    }

    private fun insertCameras() {
        viewModelScope.launch {
            camerasRepository.insertAllCameras()
        }
    }

    fun refreshCameras() {
        viewModelScope.launch {
            camerasRepository.refreshCameras()
        }
    }
}