package io.github.pedrofraca.tourapp.framework.datasource.classification

import io.github.pedrofraca.data.datasource.WriteDataSourceSingleWithParams
import io.github.pedrofraca.domain.model.StageClassificationModel
import io.github.pedrofraca.tourapp.framework.database.TourDatabase
import io.github.pedrofraca.tourapp.framework.database.classification.ClassificationDbModel

class ClassificationDbDataSource(private val db : TourDatabase) : WriteDataSourceSingleWithParams<StageClassificationModel, String> {

    override fun get(param : String): StageClassificationModel {
        TODO()
    }

    override fun save(item: StageClassificationModel) {
       item.mountain.forEach {
           db.classificationDao.insert(ClassificationDbModel(time = it.time,
                    stage = item.stage,
                    country = it.country,
                    pos = it.pos,
                    rider = it.rider,
                    team = it.team,
                    type = "mountain"))
       }
    }
}