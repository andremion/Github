package com.andremion.github.data.remote

import com.andremion.github.data.remote.dto.RepoDTO
import com.andremion.github.domain.remote.GitHubRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GitHubRemoteDataSourceImp(
    private val gitHubService: GitHubService
) : GitHubRemoteDataSource {

    override fun repos(user: String): Flow<List<RepoDTO>> = flow {
        emit(gitHubService.repos(user))
    }

}
