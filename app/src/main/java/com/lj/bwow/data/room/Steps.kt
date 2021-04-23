package com.lj.bwow.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "step_count")
data class Steps(

    val stepCount : Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,

)
