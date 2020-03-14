package io.github.pedrofraca.data.datasource.database

import androidx.room.*
import io.reactivex.Observable

@Dao
interface StageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg stages: StageDbModel?)

    @Update
    fun update(vararg stages: StageDbModel?)

    @Delete
    fun delete(stage: StageDbModel?)

    @get:Query("SELECT * FROM stage")
    val stages: Observable<List<StageDbModel>>
}