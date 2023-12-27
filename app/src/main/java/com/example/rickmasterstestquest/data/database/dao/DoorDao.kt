package com.example.rickmasterstestquest.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.rickmasterstestquest.data.database.models.Door
import kotlinx.coroutines.flow.Flow

@Dao
interface DoorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(door: Door)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(doors: List<Door>)

    @Update
    suspend fun update(door: Door)

    @Delete
    suspend fun delete(door: Door)

    @Query("SELECT * FROM Door")
    fun getAllDoors(): Flow<List<Door>>

    @Query("UPDATE Door SET favorites = 1 WHERE id = (:id)")
    fun setDoorFavorite(id: Int)

    @Query("UPDATE Door SET favorites = 0 WHERE id = (:id)")
    fun setDoorNonFavorite(id: Int)

    @Query("DELETE FROM Door")
    fun clearDoors()

    @Query("UPDATE Door SET name = (:name) WHERE id = (:id)")
    fun setDoorName(id: Int, name: String)
}