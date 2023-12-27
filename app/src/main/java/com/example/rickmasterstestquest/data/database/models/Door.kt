package com.example.rickmasterstestquest.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Door")
data class Door(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "room")
    val room: String?,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "favorites")
    val favorites: Boolean,
    @ColumnInfo(name = "snapshot")
    val snapshot: String?
)
