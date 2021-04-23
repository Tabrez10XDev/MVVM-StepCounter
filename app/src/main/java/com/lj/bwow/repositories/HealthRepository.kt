package com.lj.bwow.repositories

import androidx.lifecycle.LiveData
import com.lj.bwow.data.room.Data
import com.lj.bwow.data.HealthResponse
import com.lj.bwow.data.room.Steps
import com.lj.bwow.util.Resource

interface HealthRepository {

    suspend fun insertHealthData(healthData : Data)

    fun observeHealthData() : LiveData<Data>

    suspend fun insertStepCount(stepCount: Steps)

    fun observeStepCount() : LiveData<Steps>
    suspend fun fetchHealthData() : Resource<HealthResponse>
}