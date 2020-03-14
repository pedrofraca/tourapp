package io.github.pedrofraca.data.datasource.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stage")
data class StageDbModel(
    @PrimaryKey
    var name: String,
    var kms: String? = null,
    var winner: String? = null,
    var leader: String? = null,
    var imgUrl: String? = null,
    var isCompleted: Boolean = false)