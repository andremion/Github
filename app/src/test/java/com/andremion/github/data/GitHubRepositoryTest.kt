package com.andremion.github.data

import com.andremion.github.data.api.GitHubApi
import com.andremion.github.data.api.entity.OwnerDTO
import com.andremion.github.data.api.entity.RepoDTO
import com.andremion.github.domain.model.Repo
import com.andremion.github.util.UnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GitHubRepositoryTest : UnitTest() {

    private val gitHubApi: GitHubApi = mock()
    private val mapper: GitHubRepositoryMapper = mock()

    private val sut: GitHubRepository = GitHubRepository(gitHubApi, mapper)

    @Test
    fun `should get user repos`() = runBlockingTest {
        val user = "andremion"
        val repos = listOf(RepoDTO("name", "description", OwnerDTO("login")))
        whenever(gitHubApi.repos(user)).thenReturn(repos)
        val expected = listOf(Repo("name", "description", "owner"))
        whenever(mapper.map(repos)).thenReturn(expected)

        val actual = sut.getUserRepos(user)

        assertEquals(expected, actual)
    }

    @Test(expected = RuntimeException::class)
    fun `should throw when get exception from api`() = runBlockingTest {
        val user = "andremion"
        val error = RuntimeException()
        whenever(gitHubApi.repos(user)).thenThrow(error)

        sut.getUserRepos(user)
    }

    @Test(expected = RuntimeException::class)
    fun `should throw when get exception from mapper`() = runBlockingTest {
        val user = "andremion"
        val repos = listOf(RepoDTO("name", "description", OwnerDTO("login")))
        whenever(gitHubApi.repos(user)).thenReturn(repos)
        val error = RuntimeException()
        whenever(mapper.map(repos)).thenThrow(error)

        sut.getUserRepos(user)
    }
}
