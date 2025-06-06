package com.stephenelf.grindgym.ui.main

import com.stephenelf.grindgym.data.model.Gym

data class MainScreenState(val gymList: List<Gym> = emptyList(), val isLoading: Boolean = false, val userMessage: Int? = null)
