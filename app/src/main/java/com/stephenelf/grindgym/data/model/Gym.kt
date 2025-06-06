package com.stephenelf.grindgym.data.model

import java.util.Date

data class Gym(val open_gym_start: Date, val open_gym_end: Date, val totalFemales: Int?, val totalMales: Int?,
    val totalNonResidents: Int?, val totalResidents: Int?, val total: Int,
    val facility_title: String, val location: String, val address11: String,
    val province_code1: String, val postal_code1: String, val pass_type: String,
    val community_center: String, val open_gym: String, val group: String?)





