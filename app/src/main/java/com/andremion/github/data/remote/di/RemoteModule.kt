package com.andremion.github.data.remote.di

import com.andremion.github.data.remote.GitHubRemoteDataSourceImp
import com.andremion.github.data.remote.GitHubService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

private const val BASE_URL = "https://api.github.com/"

object RemoteModule {

    fun provideService(): GitHubService {

        val json = Json { ignoreUnknownKeys = true }
        val contentType = MediaType.get("application/json")

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()

        return retrofit.create(GitHubService::class.java)
    }

    fun provideDataSource(gitHubService: GitHubService): GitHubRemoteDataSourceImp = GitHubRemoteDataSourceImp(gitHubService)
}
