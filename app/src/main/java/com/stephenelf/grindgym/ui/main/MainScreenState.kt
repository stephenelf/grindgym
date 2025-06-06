package com.stephenelf.gymder.ui.main

import com.stephenelf.gymder.data.model.Gym

data class MainScreenState(val gymList: List<Gym> = emptyList(), val isLoading: Boolean = false, val userMessage: Int? = null)
