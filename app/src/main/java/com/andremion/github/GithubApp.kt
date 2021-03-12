package com.andremion.github

import android.app.Application
import android.content.Context
import com.andremion.github.di.AppContainer

class GithubApp : Application() {
    val appContainer: AppContainer = AppContainer()
}

val Context.appContainer: AppContainer
    get() = (applicationContext as GithubApp).appContainer
