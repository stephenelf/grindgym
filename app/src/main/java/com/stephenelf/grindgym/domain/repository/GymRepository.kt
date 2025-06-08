package com.stephenelf.grindgym.domain.repository

import com.stephenelf.grindgym.domain.model.Gym
import kotlinx.coroutines.flow.Flow

/**
 * An interface defining the contract for data operations.
 * The domain layer depends on this interface, not the concrete implementation.
 * This allows swapping the data source without affecting the business logic.
 */
interface GymRepository {

    fun getGyms(): Flow<List<Gym>>

}