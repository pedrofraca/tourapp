package com.pedrofraca.tour.framework.datasource.stage

import io.github.pedrofraca.data.datasource.WriteDataSource
import io.github.pedrofraca.domain.model.StageModel
import com.pedrofraca.tour.framework.database.stage.fromStageModel
import com.pedrofraca.tour.framework.database.stage.toStageModel

class StageDbDataSource(private val db: com.pedrofraca.tour.framework.database.TourDatabase) : WriteDataSource<StageModel> {

    override fun save(item: StageModel) {
        db.stageDao.insert(com.pedrofraca.tour.framework.database.stage.StageDbModel().fromStageModel(item))
    }

    override fun getAll(): List<StageModel> {
        return db.stageDao.stages.map {
            it.toStageModel()
        }
    }
}