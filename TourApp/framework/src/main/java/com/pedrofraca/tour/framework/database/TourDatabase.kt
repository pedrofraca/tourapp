package com.pedrofraca.tour.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.pedrofraca.tour.framework.database.classification.ClassificationDao
import com.pedrofraca.tour.framework.database.classification.ClassificationDbModel
import com.pedrofraca.tour.framework.database.stage.StageDao
import com.pedrofraca.tour.framework.database.stage.StageDbModel
import com.pedrofraca.tour.framework.database.stage.StringListConverter

@Database(entities = [StageDbModel::class, ClassificationDbModel::class], version = 10)
@TypeConverters(StringListConverter::class)
abstract class TourDatabase : RoomDatabase() {
    abstract val stageDao: StageDao
    abstract val classificationDao : ClassificationDao
}