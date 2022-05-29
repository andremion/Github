package com.andremion.github.domain.interactor

import com.andremion.github.domain.model.Repo
import kotlinx.coroutines.flow.Flow

interface GetUserReposUseCase {

    operator fun invoke(user: String): Flow<List<Repo>>
}