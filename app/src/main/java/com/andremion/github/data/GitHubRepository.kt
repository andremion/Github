package com.andremion.github.data

import com.andremion.github.data.api.GitHubApi
import com.andremion.github.domain.model.Repo

class GitHubRepository(
    private val gitHubApi: GitHubApi,
    private val mapper: GitHubRepositoryMapper
) {

    suspend fun getUserRepos(user: String): List<Repo> =
        gitHubApi.repos(user)
            .let(mapper::map)
}
