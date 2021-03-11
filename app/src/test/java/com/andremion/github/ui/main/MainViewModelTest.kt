package com.andremion.github.ui.main

import androidx.lifecycle.Observer
import com.andremion.github.domain.interactor.GetUserReposUseCase
import com.andremion.github.domain.model.Repo
import com.andremion.github.ui.main.model.RepoModel
import com.andremion.github.util.UnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest : UnitTest() {

    private val getUserRepos: GetUserReposUseCase = mock()
    private val mapper: MainViewModelMapper = mock()

    private val sut: MainViewModel = MainViewModel(getUserRepos, mapper)

    @Test
    fun `should fetch repos on init`() = runBlockingTest {
        val domainRepos = listOf(Repo("name", "description", "owner"))
        whenever(getUserRepos.invoke("andremion")).thenReturn(domainRepos)
        val modelRepos = listOf(RepoModel("name", "description", "owner"))
        whenever(mapper.map(domainRepos)).thenReturn(modelRepos)
        val observer: Observer<MainViewState> = mock()
        sut.state.observeForever(observer)

        sut.init()

        verify(observer).onChanged(MainViewState.Loading)
        verify(observer).onChanged(MainViewState.Content(modelRepos))
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun `should cover use case error`() = runBlockingTest {
        val error = RuntimeException()
        whenever(getUserRepos.invoke("andremion")).thenThrow(error)
        val observer: Observer<MainViewState> = mock()
        sut.state.observeForever(observer)

        sut.init()

        verify(observer).onChanged(MainViewState.Loading)
        verify(observer).onChanged(MainViewState.Error(error))
        verifyNoMoreInteractions(observer)
    }
}
