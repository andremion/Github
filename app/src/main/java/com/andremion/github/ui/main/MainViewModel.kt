package com.andremion.github.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andremion.github.domain.interactor.GetUserReposUseCase
import com.andremion.github.ui.main.model.RepoModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

private const val USER = "andremion"

class MainViewModel(
    private val getUserRepos: GetUserReposUseCase,
    private val mapper: MainViewModelMapper
) : ViewModel() {

    private val _state: MutableLiveData<MainViewState> = MutableLiveData(MainViewState.Loading)
    val state: LiveData<MainViewState> = _state

    fun init() {
        getUserRepos(USER)
            .map(mapper::map)
            .onEach { _state.value = MainViewState.Content(it) }
            .catch { _state.value = MainViewState.Error(it) }
            .launchIn(viewModelScope)
    }
}

sealed class MainViewState {
    object Loading : MainViewState()
    data class Content(val repos: List<RepoModel>) : MainViewState()
    data class Error(val error: Throwable) : MainViewState()
}
