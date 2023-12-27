package com.example.rickmasterstestquest

import android.app.Application
import com.example.rickmasterstestquest.data.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    companion object {
        private lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppDatabase.initDatabase(this)
    }
}