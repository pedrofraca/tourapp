package io.github.pedrofraca.tourapp.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StageDbModel::class], version = 6)
abstract class TourDatabase : RoomDatabase() {
    abstract val stageDao: StageDao
}