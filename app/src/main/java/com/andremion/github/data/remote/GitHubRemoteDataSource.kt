package com.andremion.github.data.remote

import com.andremion.github.data.remote.dto.RepoDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GitHubRemoteDataSource(
    private val gitHubService: GitHubService
) {

    fun repos(user: String): Flow<List<RepoDTO>> = flow {
        emit(gitHubService.repos(user))
    }

}
