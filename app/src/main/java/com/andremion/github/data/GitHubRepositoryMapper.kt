package com.andremion.github.data

import com.andremion.github.data.api.entity.OwnerDTO
import com.andremion.github.data.api.entity.RepoDTO
import com.andremion.github.domain.model.Repo

class GitHubRepositoryMapper {

    fun map(repos: List<RepoDTO>): List<Repo> = repos.map(RepoDTO::toDomain)
}

private fun RepoDTO.toDomain(): Repo = Repo(
    name = name,
    description = description.orEmpty(),
    owner = owner.toDomain(),
)

private fun OwnerDTO.toDomain(): String = login
