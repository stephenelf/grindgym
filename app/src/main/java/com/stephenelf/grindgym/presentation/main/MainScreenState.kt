package com.stephenelf.grindgym.presentation.main

import com.stephenelf.grindgym.data.remote.dto.GymDto
import com.stephenelf.grindgym.domain.model.Gym

data class MainScreenState(val gymList: List<Gym> = emptyList(), val isLoading: Boolean = false, val userMessage: Int? = null)
