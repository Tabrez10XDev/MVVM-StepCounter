package com.lj.bwow.data.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Data::class],
    version = 3,
    exportSchema = false
)
abstract class HealthDatabase : RoomDatabase() {

    abstract fun healthDao(): HealthDao
}