package com.andremion.github.data.remote

import com.andremion.github.data.remote.dto.RepoDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}/repos")
    suspend fun repos(@Path("user") user: String): List<RepoDTO>

}
