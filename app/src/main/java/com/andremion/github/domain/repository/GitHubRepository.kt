package com.andremion.github.domain.repository

import com.andremion.github.domain.model.Repo
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    fun getUserRepos(user: String): Flow<List<Repo>>
}