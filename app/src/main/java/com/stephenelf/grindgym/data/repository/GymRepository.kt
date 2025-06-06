package com.stephenelf.grindgym.data.repository

import com.stephenelf.grindgym.data.model.Gym
import kotlinx.coroutines.flow.Flow

interface GymRepository {

    fun getGymList(): Flow<List<Gym>>

}