package com.andremion.github.ui.main

import androidx.annotation.WorkerThread
import com.andremion.github.domain.model.Repo
import com.andremion.github.ui.main.model.RepoModel

class MainViewModelMapper {

    @WorkerThread
    fun map(repos: List<Repo>): List<RepoModel> =
        repos.map(Repo::toModel)
}

private fun Repo.toModel(): RepoModel = RepoModel(
    name = name,
    description = description,
    owner = owner,
)
