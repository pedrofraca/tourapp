package io.github.pedrofraca.tourapp.framework.database.classification

import androidx.room.*
import io.github.pedrofraca.tourapp.framework.database.stage.StageDbModel

@Dao
interface ClassificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg stages: ClassificationDbModel?)

    @Update
    fun update(vararg stages: ClassificationDbModel?)

    @Delete
    fun delete(stage: ClassificationDbModel?)

    @get:Query("SELECT * FROM classification")
    val stages: List<ClassificationDbModel>
}