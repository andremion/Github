package com.andremion.github.data.di

import com.andremion.github.data.GitHubRepositoryImp
import com.andremion.github.data.GitHubRepositoryMapperImp
import com.andremion.github.domain.mapper.GitHubRepositoryMapper
import com.andremion.github.domain.remote.GitHubRemoteDataSource
import com.andremion.github.domain.repository.GitHubRepository

object DataModule {

    fun provideMapper(): GitHubRepositoryMapper = GitHubRepositoryMapperImp()

    fun provideRepository(
        remoteDataSource: GitHubRemoteDataSource,
        mapper: GitHubRepositoryMapper
    ): GitHubRepository =
        GitHubRepositoryImp(remoteDataSource, mapper)
}
