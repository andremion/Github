package com.andremion.github.domain.remote

import com.andremion.github.data.remote.dto.RepoDTO
import kotlinx.coroutines.flow.Flow

interface GitHubRemoteDataSource {
    fun repos(user: String): Flow<List<RepoDTO>>
}