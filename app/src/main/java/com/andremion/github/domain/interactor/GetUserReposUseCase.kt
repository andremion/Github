package com.andremion.github.domain.interactor

import com.andremion.github.data.GitHubRepository
import com.andremion.github.domain.model.Repo

class GetUserReposUseCase(
    private val repository: GitHubRepository
) {

    suspend operator fun invoke(user: String): List<Repo> = repository.getUserRepos(user)
}
