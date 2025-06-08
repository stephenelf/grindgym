package com.stephenelf.grindgym.data.remote.dto

import java.util.Date
// Represents a single gym object as received from the API.
data class GymDto(val open_gym_start: Date, val open_gym_end: Date, val totalFemales: Int?, val totalMales: Int?,
                  val totalNonResidents: Int?, val totalResidents: Int?, val total: Int,
                  val facility_title: String, val location: String, val address11: String,
                  val province_code1: String, val postal_code1: String, val pass_type: String,
                  val community_center: String, val open_gym: String, val group: String?)





