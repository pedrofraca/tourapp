package io.github.pedrofraca.tourapp.framework.datasource.stage

import io.github.pedrofraca.data.datasource.WriteDataSource
import io.github.pedrofraca.domain.model.StageModel
import io.github.pedrofraca.tourapp.framework.database.TourDatabase
import io.github.pedrofraca.tourapp.framework.database.stage.StageDbModel
import io.github.pedrofraca.tourapp.framework.database.stage.fromStageModel
import io.github.pedrofraca.tourapp.framework.database.stage.toStageModel

class StageDbDataSource(private val db: TourDatabase) : WriteDataSource<StageModel> {

    override fun save(item: StageModel) {
        db.stageDao.insert(StageDbModel().fromStageModel(item))
    }

    override fun get(): List<StageModel> {
        return db.stageDao.stages.map {
            it.toStageModel()
        }
    }
}