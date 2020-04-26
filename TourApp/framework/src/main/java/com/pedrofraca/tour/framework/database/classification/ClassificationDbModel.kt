package com.pedrofraca.tour.framework.database.classification

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.pedrofraca.tour.framework.database.stage.StageDbModel

@Entity(tableName = "classification", foreignKeys =
    [ForeignKey(entity = StageDbModel::class,
                parentColumns = arrayOf("stage"),
                childColumns = arrayOf("stage"),
                onDelete = ForeignKey.CASCADE)])

data class ClassificationDbModel(
        @PrimaryKey(autoGenerate = true)
        var id : Int = 0,
        var time: String?,
        var country: String?,
        var team: String?,
        var pos: String?,
        var rider: String?,
        var type: String,
        var stage: String)