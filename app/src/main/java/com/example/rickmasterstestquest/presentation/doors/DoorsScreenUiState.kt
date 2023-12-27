package com.example.rickmasterstestquest.presentation.doors

import javax.annotation.concurrent.Immutable

@Immutable
data class DoorsScreenUiState(
    val isFavorite: Boolean,
    val name: String
) {
    companion object {
        val default = DoorsScreenUiState(
            isFavorite = false,
            name = ""
        )
    }
}