package com.andremion.github.data.api

import com.andremion.github.data.api.entity.RepoDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}/repos")
    suspend fun repos(@Path("user") user: String): List<RepoDTO>

}
