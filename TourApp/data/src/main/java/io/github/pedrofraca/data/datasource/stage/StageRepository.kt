package io.github.pedrofraca.data.datasource.stage

import io.github.pedrofraca.domain.model.StageModel

interface StagesRepository {
    val stages: List<StageModel>
}