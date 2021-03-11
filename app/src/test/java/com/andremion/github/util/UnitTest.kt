package com.andremion.github.util

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class UnitTest constructor(
    @get:Rule val coroutineTestRule: CoroutineTestRule = CoroutineTestRule(),
    @get:Rule val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()
) : TestCoroutineScope by coroutineTestRule
