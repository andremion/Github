package com.andremion.github.ui.main.di

import com.andremion.github.di.AppContainer
import com.andremion.github.di.ViewModelFactory
import com.andremion.github.domain.di.DomainModule
import com.andremion.github.ui.main.*

object MainModule {

    fun provideScreen(fragment: MainFragment): MainScreen =
        MainScreen(fragment.requireView())

    fun provideViewModelFactory(appContainer: AppContainer): ViewModelFactory =
        ViewModelFactory(
            provideViewModel(appContainer)
        )

    private fun provideViewModel(appContainer: AppContainer): MainViewModel =
        MainViewModel(
            DomainModule.provideGetUserReposUseCase(appContainer.repository),
            provideViewModelMapper()
        )

    private fun provideViewModelMapper(): MainViewModelMapper =
        MainViewModelMapperImp()
}
