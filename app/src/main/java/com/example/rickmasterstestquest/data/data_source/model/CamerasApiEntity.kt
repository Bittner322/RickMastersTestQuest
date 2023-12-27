package com.example.rickmasterstestquest.data.data_source.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CamerasApiEntity(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: Data
) {
    @Serializable
    data class Data(
        @SerialName("room")
        val room: List<String>,
        @SerialName("cameras")
        val cameras: List<Camera>
    ) {
        @Serializable
        data class Camera(
            @SerialName("name")
            val name: String,
            @SerialName("snapshot")
            val snapshot: String,
            @SerialName("room")
            val room: String?,
            @SerialName("id")
            val id: Int,
            @SerialName("favorites")
            val favorites: Boolean,
            @SerialName("rec")
            val rec: Boolean
        )
    }
}