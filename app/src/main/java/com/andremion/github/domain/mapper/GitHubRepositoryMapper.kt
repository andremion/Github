package com.andremion.github.domain.mapper


import com.andremion.github.data.remote.dto.RepoDTO
import com.andremion.github.domain.model.Repo

interface GitHubRepositoryMapper {
    fun map(repos: List<RepoDTO>): List<Repo>
}