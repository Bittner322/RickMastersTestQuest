package com.example.rickmasterstestquest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickmasterstestquest.data.database.dao.CameraDao
import com.example.rickmasterstestquest.data.database.dao.DoorDao
import com.example.rickmasterstestquest.data.database.models.Camera
import com.example.rickmasterstestquest.data.database.models.Door

@Database(entities = [Camera::class, Door::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cameraDao(): CameraDao
    abstract fun doorDao(): DoorDao

    companion object {
        lateinit var INSTANCE: AppDatabase
            private set

        fun initDatabase(context: Context) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}