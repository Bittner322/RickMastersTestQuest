package com.example.rickmasterstestquest.di

import com.example.rickmasterstestquest.core.constants.KtorConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.json

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        coerceInputValues = true
        encodeDefaults = true
        isLenient = true
        prettyPrint = true
    }

    @Provides
    fun provideHttpClient(json: Json): HttpClient = HttpClient {
        // Json parsing configs
        defaultRequest {
            contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            json(json)
        }

        // Set request timeouts
        install(HttpTimeout) {
            requestTimeoutMillis = KtorConstants.REQUEST_TIMEOUT
            connectTimeoutMillis = KtorConstants.CONNECTION_TIMEOUT
        }

        // Enable Resource style urls
        install(Resources)

        // Response stricter validation of 2xx responses
        expectSuccess = true

        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }

        // Base Url
        install(DefaultRequest) {
            url(KtorConstants.API_BASE_URL)
        }
    }
}