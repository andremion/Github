package com.andremion.github.data


import com.andremion.github.data.remote.dto.OwnerDTO
import com.andremion.github.data.remote.dto.RepoDTO
import com.andremion.github.domain.mapper.GitHubRepositoryMapper
import com.andremion.github.domain.model.Repo
import com.andremion.github.domain.remote.GitHubRemoteDataSource
import com.andremion.github.domain.repository.GitHubRepository
import com.andremion.github.util.UnitTest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class GitHubRepositoryTest : UnitTest() {

    private val mockRemoteDataSource = mock(GitHubRemoteDataSource::class.java)
    private val mockMapper = mock(GitHubRepositoryMapper::class.java)

    private val sut = mock(GitHubRepository::class.java)

    @Test
    fun `should get user repos`() = runBlockingTest {
        val user = "andremion"
        val repos = listOf(RepoDTO("name", "description", OwnerDTO("login")))
        mockRemoteDataSource.repos(user)
        Mockito.verify(mockRemoteDataSource).repos(user)
        val expected = listOf(Repo("name", "description", "owner"))
        `when`(mockMapper.map(repos)).thenReturn(expected)
    }

    @Test
    fun `should throw when get exception from remote data source`() = runBlockingTest {
        val user = "andremion"
        val error = RuntimeException()
        `when`(mockRemoteDataSource.repos(user)).thenReturn(flow { throw error })
        sut.getUserRepos(user).single()
    }


    @Test
    fun `should throw when get exception from mapper`() = runBlockingTest {
        val user = "andremion"
        val repos = listOf(RepoDTO("name", "description", OwnerDTO("login")))
        `when`(mockRemoteDataSource.repos(user)).thenReturn(flowOf(repos))
        val error = RuntimeException()
        `when`(mockMapper.map(repos)).thenThrow(error)
        sut.getUserRepos(user).single()
    }
}
