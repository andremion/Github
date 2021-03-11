package com.andremion.github.data.di

import com.andremion.github.data.GitHubRepository
import com.andremion.github.data.GitHubRepositoryMapper
import com.andremion.github.data.api.GitHubApi

object DataModule {

    fun provideMapper(): GitHubRepositoryMapper = GitHubRepositoryMapper()

    fun provideRepository(
        gitHubApi: GitHubApi,
        mapper: GitHubRepositoryMapper
    ): GitHubRepository =
        GitHubRepository(gitHubApi, mapper)
}
