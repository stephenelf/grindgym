package com.stephenelf.grindgym.data.repository

import android.util.Log.e
import com.stephenelf.grindgym.data.remote.GymApi
import com.stephenelf.grindgym.data.remote.dto.GymDto
import com.stephenelf.grindgym.domain.model.Gym
import com.stephenelf.grindgym.domain.repository.GymRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation of the GymsRepository. It fetches data from the remote
 * API service and maps it from DTOs to domain models.
 */
class GymRepositoryImpl @Inject constructor(private val gymApi: GymApi) : GymRepository {
    override fun getGyms(): Flow<List<Gym>> {
        return flow {
            val gyms = gymApi.getGyms(100).results.map { it.toDomain() }
            emit(gyms)
        }
    }

    // Mapper function to convert a DTO to a domain model.
    private fun GymDto.toDomain(): Gym {
        return Gym(
            name = facility_title ?: "Unknown Facility",
            community_center = community_center ?: "N/A",
            location = location?: "No Location",
            address = address11 ?: "No Address"
        )
    }
}
