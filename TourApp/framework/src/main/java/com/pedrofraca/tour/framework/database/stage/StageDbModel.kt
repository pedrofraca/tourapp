package com.pedrofraca.tour.framework.database.stage

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import io.github.pedrofraca.domain.model.StageModel

@Entity(tableName = "stage")
data class StageDbModel(
        @PrimaryKey
        var stage: Int = -1,
        var name: String = "",
        var kms: String? = null,
        var winner: String? = null,
        var leader: String? = null,
        var averageSpeed: String? = null,
        var imgUrl: String? = null,
        var profileImgUrl: String? = null,
        var isCompleted: Boolean = false)


class StringListConverter {
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }
}

fun StageDbModel.fromStageModel(model: StageModel): StageDbModel {
    name = model.name
    kms = model.km
    winner = model.winner
    leader = model.leader
    averageSpeed = model.averageSpeed
    profileImgUrl = model.profileImgUrl
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
            images = emptyList(),
            profileImgUrl = profileImgUrl,
            averageSpeed = averageSpeed,
            imgUrl = imgUrl,
            stage = stage)
}