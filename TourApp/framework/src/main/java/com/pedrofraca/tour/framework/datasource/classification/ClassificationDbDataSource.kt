package com.pedrofraca.tour.framework.datasource.classification

import io.github.pedrofraca.data.datasource.WriteDataSourceSingleWithParams
import io.github.pedrofraca.domain.model.ClassificationModel
import io.github.pedrofraca.domain.model.StageClassificationModel

class ClassificationDbDataSource(private val db: com.pedrofraca.tour.framework.database.TourDatabase) : WriteDataSourceSingleWithParams<StageClassificationModel, String> {

    enum class ClassificationType(type : String) {
        STAGE("stage"),
        REGULARITY("regularity"),
        MOUNTAIN("mountain"),
        GENERAL("general"),
        TEAM("team")
    }

    override fun get(param: String): StageClassificationModel {
        val classification = db.classificationDao.getClassificationForStage(param)
        val stageClassification = toClassificationModelListForType(classification, ClassificationType.STAGE.name)
        val regularityClassification = toClassificationModelListForType(classification, ClassificationType.REGULARITY.name)
        val mountainClassification = toClassificationModelListForType(classification, ClassificationType.MOUNTAIN.name)
        val generalClassification = toClassificationModelListForType(classification, ClassificationType.GENERAL.name)
        val teamClassification = toClassificationModelListForType(classification, ClassificationType.TEAM.name)
        return StageClassificationModel(mountainClassification,
                teamClassification,
                generalClassification,
                regularityClassification,
                stageClassification,
                param)
    }

    private fun toClassificationModelListForType(classification: List<com.pedrofraca.tour.framework.database.classification.ClassificationDbModel>, stage: String) =
            classification.filter { it.type == stage }.map { ClassificationModel(it.time, it.country, it.team, it.pos, it.rider) }

    override fun save(item: StageClassificationModel) {
        saveClassification(item.stageClassification, ClassificationType.STAGE.name, item.stage)
        saveClassification(item.regularity, ClassificationType.REGULARITY.name, item.stage)
        saveClassification(item.mountain, ClassificationType.MOUNTAIN.name, item.stage)
        saveClassification(item.general, ClassificationType.GENERAL.name, item.stage)
        saveClassification(item.team, ClassificationType.TEAM.name, item.stage)
    }

    private fun saveClassification(classification: List<ClassificationModel>, type: String, stage: String) {
        classification.forEach {
            db.classificationDao.insert(com.pedrofraca.tour.framework.database.classification.ClassificationDbModel(time = it.time,
                    stage = stage,
                    country = it.country,
                    pos = it.pos,
                    rider = it.rider,
                    team = it.team,
                    type = type))
        }
    }
}