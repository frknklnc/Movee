package com.example.movee.util

fun round(voteAvarage: Double): String {
    val roundoff = String.format("%.1f", voteAvarage)
    return roundoff
}