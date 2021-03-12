package com.andremion.github.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andremion.github.domain.interactor.GetUserReposUseCase
import com.andremion.github.ui.main.model.RepoModel
import kotlinx.coroutines.launch

private const val USER = "andremion"

class MainViewModel(
    private val getUserRepos: GetUserReposUseCase,
    private val mapper: MainViewModelMapper
) : ViewModel() {

    private val _state: MutableLiveData<MainViewState> = MutableLiveData(MainViewState.Loading)
    val state: LiveData<MainViewState> = _state

    fun init() {
        viewModelScope.launch {
            _state.value = try {
                val repos = getUserRepos(USER).let(mapper::map)
                MainViewState.Content(repos)
            } catch (e: Exception) {
                MainViewState.Error(e)
            }
        }
    }
}

sealed class MainViewState {
    object Loading : MainViewState()
    data class Content(val repos: List<RepoModel>) : MainViewState()
    data class Error(val error: Exception) : MainViewState()
}
