package com.lj.bwow.repositories

import androidx.lifecycle.LiveData
import com.lj.bwow.api.HealthAPI
import com.lj.bwow.data.room.Data
import com.lj.bwow.data.HealthResponse
import com.lj.bwow.data.room.HealthDao
import com.lj.bwow.util.Resource
import javax.inject.Inject

class DefaultHealthRepository @Inject constructor(
    private val healthDao: HealthDao,
    private val healthAPI: HealthAPI
) : HealthRepository {

    override suspend fun insertHealthData(healthData: Data) {
        healthDao.insertHealthData(healthData)
    }


    override fun observeHealthData(): LiveData<Data> {
        return healthDao.observeHealthData()
    }


    override suspend fun fetchHealthData(): Resource<HealthResponse> {
        return try {
            val response = healthAPI.getHealthData()
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An Unknown error occured", null)
            } else{
                Resource.error("An Unknown error occured", null)
            }
        }catch (e : Exception){
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}