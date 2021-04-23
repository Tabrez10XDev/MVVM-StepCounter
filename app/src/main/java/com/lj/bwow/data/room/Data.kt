package com.lj.bwow.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "health_data")
data class Data(

    @SerializedName("heart-rate")
    val heartRate: String,

    @SerializedName("sleep-time")
    val sleepTime: String,

    @SerializedName("training-time")
    val trainingTime: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 2
)