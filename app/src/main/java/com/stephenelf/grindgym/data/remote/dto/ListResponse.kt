package com.stephenelf.grindgym.data.remote.dto

data class ListResponse<T>(val total_count: Int, val results: List<T>)

