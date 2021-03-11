package com.andremion.github.data.api.di

import com.andremion.github.data.api.GitHubApi
import com.andremion.github.data.api.GitHubService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

private const val BASE_URL = "https://api.github.com/"

object ApiModule {

    fun provideService(): GitHubService {

        val json = Json { ignoreUnknownKeys = true }
        val contentType = MediaType.get("application/json")

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()

        return retrofit.create(GitHubService::class.java)
    }

    fun provideApi(gitHubService: GitHubService): GitHubApi = GitHubApi(gitHubService)
}
