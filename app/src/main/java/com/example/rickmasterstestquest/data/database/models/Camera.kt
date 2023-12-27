package com.example.rickmasterstestquest.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Camera")
data class Camera(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "snapshot")
    val snapshot: String,
    @ColumnInfo(name = "room")
    val room: String?,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "favorites")
    val favorites: Boolean,
    @ColumnInfo(name = "rec")
    val rec: Boolean
)
