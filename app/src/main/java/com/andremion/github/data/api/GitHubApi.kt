package com.andremion.github.data.api

import com.andremion.github.data.api.entity.RepoDTO

class GitHubApi(
    private val gitHubService: GitHubService
) {

    suspend fun repos(user: String): List<RepoDTO> = gitHubService.repos(user)

}
