package io.github.pedrofraca.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StageDbModel::class], version = 4)
abstract class StageDatabase : RoomDatabase() {
    abstract val stageDao: StageDao
}