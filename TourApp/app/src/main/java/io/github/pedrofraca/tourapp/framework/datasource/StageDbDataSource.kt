package io.github.pedrofraca.tourapp.framework.datasource

import io.github.pedrofraca.data.datasource.WriteDataSource
import io.github.pedrofraca.domain.model.StageModel
import io.github.pedrofraca.tourapp.framework.database.TourDatabase
import io.github.pedrofraca.tourapp.framework.database.StageDbModel
import io.github.pedrofraca.tourapp.framework.database.fromStageModel
import io.github.pedrofraca.tourapp.framework.database.toStageModel

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