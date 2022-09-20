package com.pedrofraca.tour.framework.datasource.classification

import io.github.pedrofraca.data.datasource.ReadOnlyDataSourceWithFilter
import io.github.pedrofraca.domain.model.ClassificationModel
import io.github.pedrofraca.domain.model.StageClassificationModel
import io.github.pedrofraca.tour.api.ServiceFactory
import io.github.pedrofraca.tour.api.TourScrappingService
import io.github.pedrofraca.tour.api.model.Classification

class ClassificationApiDataSource (private val api: TourScrappingService = ServiceFactory().build(TourScrappingService::class.java)): ReadOnlyDataSourceWithFilter<StageClassificationModel, String> {
    override fun get(param: String): StageClassificationModel {
        val api = api.getClassificationForStage(param).blockingGet()

        return StageClassificationModel(api.mountain.map { it.toClassificationModel() },
                api.team.map { it.toClassificationModel() },
                api.general.map { it.toClassificationModel() },
                api.regularity.map { it.toClassificationModel() },
                api.stageClassification.map { it.toClassificationModel() },
                param)
    }

    override fun getAll(): List<StageClassificationModel> {
        TODO("Not yet implemented")
    }

}

fun Classification.toClassificationModel() : ClassificationModel {
    return ClassificationModel(this.time, this.country, this.team, this.pos, this.rider)
}