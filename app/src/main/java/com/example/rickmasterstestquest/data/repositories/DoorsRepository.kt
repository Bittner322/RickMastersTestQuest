package com.example.rickmasterstestquest.data.repositories

import com.example.rickmasterstestquest.data.data_source.ApiDataSource
import com.example.rickmasterstestquest.data.database.AppDatabase
import com.example.rickmasterstestquest.data.database.models.Door
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DoorsRepository @Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val database: AppDatabase
) {
    suspend fun insertAllDoors() {
        withContext(Dispatchers.IO) {
            database.doorDao().insertAll(mapDoors())
        }
    }

    private suspend fun mapDoors(): List<Door> {
        val mappedDoors = apiDataSource.getDoors().data.map {
            Door(
                name = it.name,
                snapshot = it.snapshot,
                room = it.room,
                id = it.id,
                favorites = it.favorites
            )
        }
        return mappedDoors
    }

    fun getAllDoors(): Flow<List<Door>> {
        return database.doorDao().getAllDoors().flowOn(Dispatchers.IO)
    }

    suspend fun updateFavoriteDoor(door: Door) {
        withContext(Dispatchers.IO) {
            if (door.favorites) {
                database.doorDao().setDoorNonFavorite(door.id)
            } else {
                database.doorDao().setDoorFavorite(door.id)
            }
        }
    }

    suspend fun refreshDoors() {
        withContext(Dispatchers.IO) {
            database.doorDao().clearDoors()
            insertAllDoors()
        }
    }

    suspend fun updateDoorName(id: Int, name: String) {
        withContext(Dispatchers.IO) {
            database.doorDao().setDoorName(
                id = id,
                name = name
            )
        }
    }
}