package com.andremion.github.ui.main

import androidx.lifecycle.Observer
import com.andremion.github.domain.interactor.GetUserReposUseCase
import com.andremion.github.domain.model.Repo
import com.andremion.github.ui.main.model.RepoModel
import com.andremion.github.util.UnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class MainViewModelTest : UnitTest() {

    private val mockGetUserRepos = mock(GetUserReposUseCase::class.java)
    private val mockMapper = mock(MainViewModelMapper::class.java)

    @Mock
    lateinit var observer: Observer<MainViewState>
    private val sut: MainViewModel = MainViewModel(mockGetUserRepos, mockMapper)


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        sut.state.observeForever(observer)
    }


    @Test
    fun `should fetch repos on init`() = runBlockingTest {
        val domainRepos = listOf(Repo("name", "description", "owner"))
        `when`(mockGetUserRepos.invoke("andremion")).thenReturn(flowOf(domainRepos))
        val modelRepos = listOf(RepoModel("name", "description", "owner"))
        `when`(mockMapper.map(domainRepos)).thenReturn(modelRepos)

        sut.init()
        verify(observer).onChanged(MainViewState.Loading)
        verify(observer).onChanged(MainViewState.Content(modelRepos))
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun `should cover use case error`() = runBlockingTest {
        val error = RuntimeException()
        `when`(mockGetUserRepos.invoke("andremion")).thenReturn(flow { throw error })

        sut.init()
        verify(observer).onChanged(MainViewState.Loading)
        verify(observer).onChanged(MainViewState.Error(error))
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun `should cover mapper error`() = runBlockingTest {
        val domainRepos = listOf(Repo("name", "description", "owner"))
        `when`(mockGetUserRepos.invoke("andremion")).thenReturn(flowOf(domainRepos))
        val error = RuntimeException()
        `when`(mockMapper.map(domainRepos)).thenThrow(error)

        sut.init()
        verify(observer).onChanged(MainViewState.Loading)
        verify(observer).onChanged(MainViewState.Error(error))
        verifyNoMoreInteractions(observer)
    }
}
