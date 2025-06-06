package com.stephenelf.gymder.data.repository

import com.stephenelf.gymder.data.model.Gym
import kotlinx.coroutines.flow.Flow

interface GymRepository {

    fun getGymList(): Flow<List<Gym>>

}