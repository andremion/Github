package com.andremion.github.domain.interactor

import com.andremion.github.domain.model.Repo
import com.andremion.github.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow

class GetUserReposUseCaseImp(
    private val repository: GitHubRepository
):GetUserReposUseCase {
    override operator fun invoke(user: String): Flow<List<Repo>> = repository.getUserRepos(user)
}
