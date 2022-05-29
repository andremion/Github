package com.andremion.github.data

import com.andremion.github.domain.mapper.GitHubRepositoryMapper
import com.andremion.github.domain.model.Repo
import com.andremion.github.domain.remote.GitHubRemoteDataSource
import com.andremion.github.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GitHubRepositoryImp(
    private val remoteDataSource: GitHubRemoteDataSource,
    private val mapper: GitHubRepositoryMapper
):GitHubRepository {

    override fun getUserRepos(user: String): Flow<List<Repo>> =
        remoteDataSource.repos(user)
            .map(mapper::map)
}
