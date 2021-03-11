package com.andremion.github.ui.main.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andremion.github.data.api.di.ApiModule
import com.andremion.github.data.di.DataModule
import com.andremion.github.domain.di.DomainModule
import com.andremion.github.domain.interactor.GetUserReposUseCase
import com.andremion.github.ui.main.MainScreen
import com.andremion.github.ui.main.MainViewModel
import com.andremion.github.ui.main.MainViewModelMapper

object MainModule {

    fun provideMapper(): MainViewModelMapper = MainViewModelMapper()

    fun provideMainViewModel(getUserReposUseCase: GetUserReposUseCase, mapper: MainViewModelMapper): MainViewModel =
        MainViewModel(getUserReposUseCase, mapper)

    fun provideScreen(fragment: Fragment): MainScreen =
        MainScreen(fragment.requireView())
}

class MainViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MainModule.provideMainViewModel(
            DomainModule.provideGetUserReposUseCase(
                DataModule.provideRepository(
                    ApiModule.provideApi(
                        ApiModule.provideService()
                    ),
                    DataModule.provideMapper()
                )
            ),
            MainModule.provideMapper()
        ) as T
}
