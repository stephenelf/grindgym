package com.stephenelf.grindgym.presentation.util


import android.location.Location
import java.util.Random
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

fun generateRandomLocationInsideRadius(x0: Double, y0: Double, radius: Int): Location {
    val random = Random()
    // Convert radius from meters to degrees
    val radiusInDegrees = (radius / 111000f).toDouble()
    val u = random.nextDouble()
    val v = random.nextDouble()
    val w = radiusInDegrees * sqrt(u)
    val t = 2.0 * Math.PI * v
    val x = w * cos(t)
    val y = w * sin(t)
    // Adjust the x-coordinate for the shrinking of the east-west distances
    val newX = x / cos(Math.toRadians(y0))
    val foundLongitude = newX + x0
    val foundLatitude = y + y0
    val location = Location("")
    location.latitude = foundLatitude
    location.longitude = foundLongitude
    return location
}