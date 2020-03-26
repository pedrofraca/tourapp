package io.github.pedrofraca.domain.model


data class StageClassificationModel(
        var mountain: List<ClassificationModel>,
        var team: List<ClassificationModel>,
        var general: List<ClassificationModel>,
        var regularity: List<ClassificationModel>,
        var stage: List<ClassificationModel>)