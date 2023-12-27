package com.example.rickmasterstestquest.data.data_source

import com.example.rickmasterstestquest.data.data_source.model.CamerasApiEntity
import com.example.rickmasterstestquest.data.data_source.model.DoorsApiEntity
import com.example.rickmasterstestquest.data.data_source.resources.CprogroupResources
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import javax.inject.Inject

class ApiDataSource @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun getCameras(): CamerasApiEntity =
        httpClient
            .get(resource = CprogroupResources.Rubetek.Cameras())
            .body()

    suspend fun getDoors(): DoorsApiEntity =
        httpClient
            .get(resource = CprogroupResources.Rubetek.Doors())
            .body()
}