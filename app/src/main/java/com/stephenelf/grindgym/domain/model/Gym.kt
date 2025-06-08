package com.stephenelf.grindgym.domain.model

import java.util.Date

/**
 * Represents the clean domain model for a Gym. This is the object
 * that the UI and business logic will work with, completely decoupled
 * from the data layer's DTOs.
 */
data class Gym(
    val id: String = java.util.UUID.randomUUID().toString(), // Add a unique ID for stable keys in Compose
    val name: String,
    val community_center: String,
    val location: String,
    val address: String
)
