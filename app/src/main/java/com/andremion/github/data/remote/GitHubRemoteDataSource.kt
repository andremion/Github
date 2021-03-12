package com.andremion.github.data.remote

import com.andremion.github.data.remote.dto.RepoDTO

class GitHubRemoteDataSource(
    private val gitHubService: GitHubService
) {

    suspend fun repos(user: String): List<RepoDTO> = gitHubService.repos(user)

}
