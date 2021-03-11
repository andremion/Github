package com.andremion.github.domain.di

import com.andremion.github.data.GitHubRepository
import com.andremion.github.domain.interactor.GetUserReposUseCase

object DomainModule {

    fun provideGetUserReposUseCase(repository: GitHubRepository): GetUserReposUseCase =
        GetUserReposUseCase(repository)
}
