package com.andremion.github.domain.di

import com.andremion.github.domain.interactor.GetUserReposUseCase
import com.andremion.github.domain.interactor.GetUserReposUseCaseImp
import com.andremion.github.domain.repository.GitHubRepository

object DomainModule {
    fun provideGetUserReposUseCase(repository: GitHubRepository): GetUserReposUseCase =
        GetUserReposUseCaseImp(repository)
}
