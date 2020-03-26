package io.github.pedrofraca.data.datasource.classification

import io.github.pedrofraca.domain.model.StageClassificationModel

interface ClassificationRepository {
    fun getClassificationForStage(stage : String) : StageClassificationModel
}