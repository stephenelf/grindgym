package com.stephenelf.gymder.data.model

data class ListResponse<T>(val total_count: Int, val results: List<T>)

