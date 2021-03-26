package com.andremion.github.data

import com.andremion.github.data.remote.GitHubRemoteDataSource
import com.andremion.github.domain.model.Repo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GitHubRepository(
    private val remoteDataSource: GitHubRemoteDataSource,
    private val mapper: GitHubRepositoryMapper
) {

    fun getUserRepos(user: String): Flow<List<Repo>> =
        remoteDataSource.repos(user)
            .map(mapper::map)
}
