package com.andremion.github.domain.interactor

import com.andremion.github.data.GitHubRepository
import com.andremion.github.domain.model.Repo
import kotlinx.coroutines.flow.Flow

class GetUserReposUseCase(
    private val repository: GitHubRepository
) {

    operator fun invoke(user: String): Flow<List<Repo>> = repository.getUserRepos(user)
}
