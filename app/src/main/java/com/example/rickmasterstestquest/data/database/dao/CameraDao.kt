package com.example.rickmasterstestquest.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.rickmasterstestquest.data.database.models.Camera
import kotlinx.coroutines.flow.Flow

@Dao
interface CameraDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(camera: Camera)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(cameras: List<Camera>)

    @Update
    suspend fun update(camera: Camera)

    @Delete
    suspend fun delete(camera: Camera)

    @Query("SELECT * FROM Camera")
    fun getAllCameras(): Flow<List<Camera>>

    @Query("UPDATE Camera SET favorites = 1 WHERE id = (:id)")
    fun setCameraFavorite(id: Int)

    @Query("UPDATE Camera SET favorites = 0 WHERE id = (:id)")
    fun setCameraNonFavorite(id: Int)

    @Query("DELETE FROM Camera")
    fun clearCameras()
}