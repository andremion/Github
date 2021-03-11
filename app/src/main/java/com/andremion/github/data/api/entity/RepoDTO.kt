package com.andremion.github.data.api.entity

import kotlinx.serialization.Serializable

@Serializable
data class RepoDTO(
    val name: String,
    val description: String?,
    val owner: OwnerDTO,
)
