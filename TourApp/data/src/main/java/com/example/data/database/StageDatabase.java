package com.example.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {StageDbModel.class}, version = 3)
public abstract class StageDatabase extends RoomDatabase {
    public abstract StageDataSource getStageDao();
}
