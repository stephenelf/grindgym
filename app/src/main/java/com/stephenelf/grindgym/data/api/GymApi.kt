package com.stephenelf.grindgym.data.api

import com.stephenelf.grindgym.data.model.Gym
import com.stephenelf.grindgym.data.model.ListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GymApi {

    @GET("catalog/datasets/open-gym/records")
    suspend fun getGyms(@Query("limit") limit: Int): ListResponse<Gym>
}