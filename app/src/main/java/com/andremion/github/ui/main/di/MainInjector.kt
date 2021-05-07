package com.andremion.github.ui.main.di

import com.andremion.github.appContainer
import com.andremion.github.ui.main.MainFragment

object MainInjector {

    fun inject(fragment: MainFragment) {
        val appContainer = fragment.requireContext().appContainer
        fragment.screen = MainModule.provideScreen(fragment)
        fragment.viewModelFactory = MainModule.provideViewModelFactory(appContainer)
    }
}
