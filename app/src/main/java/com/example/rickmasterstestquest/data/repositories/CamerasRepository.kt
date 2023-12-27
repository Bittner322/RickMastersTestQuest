package com.example.rickmasterstestquest.data.repositories

import com.example.rickmasterstestquest.data.data_source.ApiDataSource
import com.example.rickmasterstestquest.data.database.AppDatabase
import com.example.rickmasterstestquest.data.database.models.Camera
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CamerasRepository @Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val database: AppDatabase
) {
    suspend fun insertAllCameras() {
        withContext(Dispatchers.IO) {
            database.cameraDao().insertAll(mapCameras())
        }
    }

    private suspend fun mapCameras(): List<Camera> {
        val mappedCameras = apiDataSource.getCameras().data.cameras.map {
            Camera(
                name = it.name,
                snapshot = it.snapshot,
                room = it.room,
                id = it.id,
                favorites = it.favorites,
                rec = it.rec
            )
        }
        return mappedCameras
    }

    fun getAllCameras(): Flow<List<Camera>> {
        return database.cameraDao().getAllCameras().flowOn(Dispatchers.IO)
    }

    suspend fun updateFavoriteCamera(camera: Camera) {
        withContext(Dispatchers.IO) {
            if (camera.favorites) {
                database.cameraDao().setCameraNonFavorite(camera.id)
            } else {
                database.cameraDao().setCameraFavorite(camera.id)
            }
        }
    }

    suspend fun refreshCameras() {
        withContext(Dispatchers.IO) {
            database.cameraDao().clearCameras()
            insertAllCameras()
        }
    }
}