package io.github.pedrofraca.data.datasource.database;

import android.content.Context;

import androidx.room.Room;

public class StageDatabaseFactory {

    public static StageDatabase getDatabase(Context context){
        return Room.databaseBuilder(context, StageDatabase.class, "db-contacts")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

}
