package com.pedrofraca.tour.framework.database

import android.content.Context
import androidx.room.Room

object TourDatabaseFactory {
    fun getDatabase(context: Context): TourDatabase {
        return Room.databaseBuilder(context, TourDatabase::class.java, "tour-db")
                .fallbackToDestructiveMigration()
                .build()
    }
}