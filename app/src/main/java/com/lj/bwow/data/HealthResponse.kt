package com.lj.bwow.data

import com.lj.bwow.data.room.Data

data class HealthResponse(
    val code: Int,
    val `data`: Data,
    val success: String
)