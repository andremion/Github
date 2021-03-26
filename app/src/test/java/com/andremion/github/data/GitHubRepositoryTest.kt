package com.andremion.github.data

import com.andremion.github.data.remote.GitHubRemoteDataSource
import com.andremion.github.data.remote.dto.OwnerDTO
import com.andremion.github.data.remote.dto.RepoDTO
import com.andremion.github.domain.model.Repo
import com.andremion.github.util.UnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GitHubRepositoryTest : UnitTest() {

    private val mockRemoteDataSource: GitHubRemoteDataSource = mock()
    private val mockMapper: GitHubRepositoryMapper = mock()

    private val sut: GitHubRepository = GitHubRepository(mockRemoteDataSource, mockMapper)

    @Test
    fun `should get user repos`() = runBlockingTest {
        val user = "andremion"
        val repos = listOf(RepoDTO("name", "description", OwnerDTO("login")))
        whenever(mockRemoteDataSource.repos(user)).thenReturn(flowOf(repos))
        val expected = listOf(Repo("name", "description", "owner"))
        whenever(mockMapper.map(repos)).thenReturn(expected)

        val actual = sut.getUserRepos(user).single()

        assertEquals(expected, actual)
    }

    @Test(expected = RuntimeException::class)
    fun `should throw when get exception from remote data source`() = runBlockingTest {
        val user = "andremion"
        val error = RuntimeException()
        whenever(mockRemoteDataSource.repos(user)).thenReturn(flow { throw error })

        sut.getUserRepos(user).single()
    }

    @Test(expected = RuntimeException::class)
    fun `should throw when get exception from mapper`() = runBlockingTest {
        val user = "andremion"
        val repos = listOf(RepoDTO("name", "description", OwnerDTO("login")))
        whenever(mockRemoteDataSource.repos(user)).thenReturn(flowOf(repos))
        val error = RuntimeException()
        whenever(mockMapper.map(repos)).thenThrow(error)

        sut.getUserRepos(user).single()
    }
}
