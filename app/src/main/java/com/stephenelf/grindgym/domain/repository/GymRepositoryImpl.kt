package com.stephenelf.grindgym.domain.repository

import com.stephenelf.grindgym.data.api.GymApi
import com.stephenelf.grindgym.data.model.Gym
import com.stephenelf.grindgym.data.repository.GymRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GymRepositoryImpl @Inject constructor(private val gymApi: GymApi) : GymRepository {
    override fun getGymList(): Flow<List<Gym>> {
        return flow {
            emit(gymApi.getGyms(100).results)
        }
    }
}