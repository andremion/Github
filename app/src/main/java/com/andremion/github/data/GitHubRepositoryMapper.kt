package com.andremion.github.data

import androidx.annotation.WorkerThread
import com.andremion.github.data.remote.dto.OwnerDTO
import com.andremion.github.data.remote.dto.RepoDTO
import com.andremion.github.domain.model.Repo

class GitHubRepositoryMapper {

    @WorkerThread
    fun map(repos: List<RepoDTO>): List<Repo> = repos.map(RepoDTO::toDomain)
}

private fun RepoDTO.toDomain(): Repo = Repo(
    name = name,
    description = description.orEmpty(),
    owner = owner.toDomain(),
)

private fun OwnerDTO.toDomain(): String = login
