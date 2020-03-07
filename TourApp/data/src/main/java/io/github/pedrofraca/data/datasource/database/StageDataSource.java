package io.github.pedrofraca.data.datasource.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StageDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE) void insert(StageDbModel... stages);

    @Update void update(StageDbModel... stages);

    @Delete void delete(StageDbModel stage);

    @Query("SELECT * FROM stage") LiveData<List<StageDbModel>> getStages();
}
