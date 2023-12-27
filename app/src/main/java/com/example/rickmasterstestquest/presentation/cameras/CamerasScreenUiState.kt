package com.example.rickmasterstestquest.presentation.cameras

import javax.annotation.concurrent.Immutable

@Immutable
data class CamerasScreenUiState(
    val isFavorite: Boolean
) {
    companion object {
        val default = CamerasScreenUiState(
            isFavorite = false
        )
    }
}
