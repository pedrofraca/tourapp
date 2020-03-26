package io.github.pedrofraca.tourapp.framework.database

import android.content.Context
import androidx.room.Room

object StageDatabaseFactory {
    fun getDatabase(context: Context?): TourDatabase {
        return Room.databaseBuilder(context!!, TourDatabase::class.java, "tour-db")
                .fallbackToDestructiveMigration()
                .build()
    }
}