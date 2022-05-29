package com.andremion.github.di

import com.andremion.github.data.di.DataModule
import com.andremion.github.data.remote.GitHubService
import com.andremion.github.data.remote.di.RemoteModule
import com.andremion.github.domain.mapper.GitHubRepositoryMapper
import com.andremion.github.domain.remote.GitHubRemoteDataSource
import com.andremion.github.domain.repository.GitHubRepository

class AppContainer {

    private val gitHubService: GitHubService = RemoteModule.provideService()
    private val remoteDataSource: GitHubRemoteDataSource = RemoteModule.provideDataSource(gitHubService)
    private val repositoryMapper: GitHubRepositoryMapper = DataModule.provideMapper()
    val repository: GitHubRepository = DataModule.provideRepository(remoteDataSource, repositoryMapper)
}
