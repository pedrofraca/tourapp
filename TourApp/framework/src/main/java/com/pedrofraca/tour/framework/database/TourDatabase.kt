package com.pedrofraca.tour.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pedrofraca.tour.framework.database.classification.ClassificationDao
import com.pedrofraca.tour.framework.database.classification.ClassificationDbModel
import com.pedrofraca.tour.framework.database.stage.StageDao
import com.pedrofraca.tour.framework.database.stage.StageDbModel

@Database(entities = [StageDbModel::class, ClassificationDbModel::class], version = 7)
abstract class TourDatabase : RoomDatabase() {
    abstract val stageDao: StageDao
    abstract val classificationDao : ClassificationDao
}