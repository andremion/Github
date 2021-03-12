package com.andremion.github.data.di

import com.andremion.github.data.GitHubRepository
import com.andremion.github.data.GitHubRepositoryMapper
import com.andremion.github.data.remote.GitHubRemoteDataSource

object DataModule {

    fun provideMapper(): GitHubRepositoryMapper = GitHubRepositoryMapper()

    fun provideRepository(
        remoteDataSource: GitHubRemoteDataSource,
        mapper: GitHubRepositoryMapper
    ): GitHubRepository =
        GitHubRepository(remoteDataSource, mapper)
}
