package com.stephenelf.gymder

import com.stephenelf.gymder.data.repository.GymRepository
import com.stephenelf.gymder.ui.main.MainScreenViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class MainScreenViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var gymRepository: GymRepository

    @Inject
    lateinit var mainScreenViewModel: MainScreenViewModel

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testViewModel(){
        runBlocking{
            mainScreenViewModel.refresh()
            assert(mainScreenViewModel.uiState.value.gymList.isNotEmpty())
            assert(mainScreenViewModel.uiState.value.gymList.size == 100)
        }
    }
}