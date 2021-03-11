package com.andremion.github.di

import androidx.lifecycle.ViewModelProvider
import com.andremion.github.ui.main.MainFragment
import com.andremion.github.ui.main.MainViewModel
import com.andremion.github.ui.main.di.MainModule
import com.andremion.github.ui.main.di.MainViewModelFactory

object DI {

    fun inject(fragment: MainFragment) {
        fragment.viewModel = ViewModelProvider(fragment, MainViewModelFactory()).get(MainViewModel::class.java)
        fragment.screen = MainModule.provideScreen(fragment)
    }
}
