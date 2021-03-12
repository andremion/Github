package com.andremion.github.ui.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.andremion.github.di.AppContainer
import com.andremion.github.domain.di.DomainModule
import com.andremion.github.ui.main.MainFragment
import com.andremion.github.ui.main.MainScreen
import com.andremion.github.ui.main.MainViewModel
import com.andremion.github.ui.main.MainViewModelMapper

object MainModule {

    fun provideViewModel(appContainer: AppContainer, viewModelStoreOwner: ViewModelStoreOwner): MainViewModel =
        provideViewModelProvider(appContainer, viewModelStoreOwner).get(MainViewModel::class.java)

    fun provideScreen(fragment: MainFragment): MainScreen = MainScreen(fragment.requireView())

    private fun provideViewModelMapper(): MainViewModelMapper = MainViewModelMapper()

    private fun provideViewModelProvider(
        appContainer: AppContainer,
        viewModelStoreOwner: ViewModelStoreOwner
    ): ViewModelProvider =
        ViewModelProvider(viewModelStoreOwner, MainViewModelFactory(appContainer))

    private class MainViewModelFactory(private val appContainer: AppContainer) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            MainViewModel(
                DomainModule.provideGetUserReposUseCase(appContainer.repository),
                provideViewModelMapper()
            ) as T
    }
}
