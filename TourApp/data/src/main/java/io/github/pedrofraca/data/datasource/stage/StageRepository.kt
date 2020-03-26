package io.github.pedrofraca.data.datasource.stage

import io.github.pedrofraca.domain.model.StageModel

interface StageRepository {
    val stages: List<StageModel>
    fun refresh() : List<StageModel>
}