package com.andremion.github.data

import com.andremion.github.data.remote.GitHubRemoteDataSource
import com.andremion.github.domain.model.Repo

class GitHubRepository(
    private val remoteDataSource: GitHubRemoteDataSource,
    private val mapper: GitHubRepositoryMapper
) {

    suspend fun getUserRepos(user: String): List<Repo> =
        remoteDataSource.repos(user)
            .let(mapper::map)
}
