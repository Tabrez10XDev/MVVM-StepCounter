package com.lj.bwow.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HealthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHealthData(healthData : Data)

    @Query("SELECT * FROM health_data")
    fun observeHealthData(): LiveData<Data>

}