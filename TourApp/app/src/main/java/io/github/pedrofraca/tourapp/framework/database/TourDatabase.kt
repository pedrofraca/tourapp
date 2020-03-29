package io.github.pedrofraca.tourapp.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.pedrofraca.tourapp.framework.database.classification.ClassificationDao
import io.github.pedrofraca.tourapp.framework.database.classification.ClassificationDbModel
import io.github.pedrofraca.tourapp.framework.database.stage.StageDao
import io.github.pedrofraca.tourapp.framework.database.stage.StageDbModel

@Database(entities = [StageDbModel::class, ClassificationDbModel::class], version = 7)
abstract class TourDatabase : RoomDatabase() {
    abstract val stageDao: StageDao
    abstract val classificationDao : ClassificationDao
}