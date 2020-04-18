package com.pedrofraca.tour.framework.database.classification

import androidx.room.*

@Dao
interface ClassificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg stages: ClassificationDbModel?)

    @Update
    fun update(vararg stages: ClassificationDbModel?)

    @Delete
    fun delete(stage: ClassificationDbModel?)

    @Query("SELECT * FROM classification WHERE stage = :stage")
    fun getClassificationForStage(stage: String): List<ClassificationDbModel>
}