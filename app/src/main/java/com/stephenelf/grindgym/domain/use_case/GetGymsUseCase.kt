package com.stephenelf.grindgym.domain.use_case

import com.stephenelf.grindgym.domain.model.Gym
import com.stephenelf.grindgym.domain.repository.GymRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * The use case for getting a list of gyms. This class contains the specific
 * business logic for this feature. It orchestrates the flow of data from
 * the repository and prepares it for the presentation layer.
 */
class GetGymsUseCase @Inject constructor(
    private val repository: GymRepository
) {
    suspend operator fun invoke(): Flow<List<Gym>> {
        // Here you could add more business logic, like filtering, sorting, etc.
        return repository.getGyms()
    }
}