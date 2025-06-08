package com.stephenelf.grindgym.data.remote

import com.stephenelf.grindgym.data.remote.dto.GymDto
import com.stephenelf.grindgym.data.remote.dto.ListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GymApi {

    @GET("catalog/datasets/open-gym/records")
    suspend fun getGyms(@Query("limit") limit: Int): ListResponse<GymDto>
}