package com.example.rickmasterstestquest.data.data_source.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DoorsApiEntity(
    @SerialName("success")
    val success: Boolean,
    @SerialName("data")
    val data: List<Data>
) {
    @Serializable
    data class Data(
        @SerialName("name")
        val name: String,
        @SerialName("room")
        val room: String?,
        @SerialName("id")
        val id: Int,
        @SerialName("favorites")
        val favorites: Boolean,
        @SerialName("snapshot")
        val snapshot: String?
    )
}