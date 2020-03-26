package io.github.pedrofraca.tourapp.framework.database

import androidx.room.*
import io.github.pedrofraca.tourapp.framework.database.StageDbModel

@Dao
interface StageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg stages: StageDbModel?)

    @Update
    fun update(vararg stages: StageDbModel?)

    @Delete
    fun delete(stage: StageDbModel?)

    @get:Query("SELECT * FROM stage")
    val stages: List<StageDbModel>
}