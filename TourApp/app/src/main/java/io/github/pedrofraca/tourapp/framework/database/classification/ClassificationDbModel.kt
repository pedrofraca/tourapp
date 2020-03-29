package io.github.pedrofraca.tourapp.framework.database.classification

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import io.github.pedrofraca.tourapp.framework.database.stage.StageDbModel

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

//fun StageDbModel.fromStageModel(model: StageModel): StageDbModel {
//    name = model.name
//    kms = model.km
//    winner = model.winner
//    leader = model.leader
//    averageSpeed = model.averageSpeed
//    imgUrl = model.imgUrl
//    isCompleted = model.completed()
//    stage = model.stage
//    return this
//}
//
//fun StageDbModel.toStageModel(): StageModel {
//    return StageModel(name = name,
//            km = kms,
//            winner = winner,
//            leader = leader,
//            averageSpeed = averageSpeed,
//            imgUrl = imgUrl,
//            stage = stage)
//}