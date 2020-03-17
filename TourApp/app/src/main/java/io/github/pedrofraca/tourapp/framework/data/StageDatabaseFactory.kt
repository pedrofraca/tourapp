package io.github.pedrofraca.data.datasource.database

import android.content.Context
import androidx.room.Room

object StageDatabaseFactory {
    fun getDatabase(context: Context?): StageDatabase {
        return Room.databaseBuilder(context!!, StageDatabase::class.java, "tour-db")
                .fallbackToDestructiveMigration()
                .build()
    }
}