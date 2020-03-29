package io.github.pedrofraca.tourapp.framework.database.stage

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.pedrofraca.domain.model.StageModel

@Entity(tableName = "stage")
data class StageDbModel(
        @PrimaryKey
        var stage: String = "",
        var name: String = "",
        var kms: String? = null,
        var winner: String? = null,
        var leader: String? = null,
        var averageSpeed: String? = null,
        var imgUrl: String? = null,
        var isCompleted: Boolean = false)

fun StageDbModel.fromStageModel(model: StageModel): StageDbModel {
    name = model.name
    kms = model.km
    winner = model.winner
    leader = model.leader
    averageSpeed = model.averageSpeed
    imgUrl = model.imgUrl
    isCompleted = model.completed()
    stage = model.stage
    return this
}

fun StageDbModel.toStageModel(): StageModel {
    return StageModel(name = name,
            km = kms,
            winner = winner,
            leader = leader,
            averageSpeed = averageSpeed,
            imgUrl = imgUrl,
            stage = stage)
}