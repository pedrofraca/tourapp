package io.github.pedrofraca.data.datasource.stage

import io.github.pedrofraca.data.datasource.WriteDataSource
import io.github.pedrofraca.data.datasource.database.StageDatabase
import io.github.pedrofraca.data.datasource.database.StageDbModel
import io.github.pedrofraca.domain.model.StageModel
import io.reactivex.Observable

class StageDbDataSource(private val db: StageDatabase) : WriteDataSource<StageModel> {

    override fun save(item: StageModel) {
        db.stageDao.insert(StageDbModel(item.name, item.km, item.winner, item.leader, item.imgUrl, item.completed()))
    }

    override fun get(): Observable<List<StageModel>> {
        return db.stageDao.stages
                .map {
                    val list = ArrayList<StageModel>()
                    it.forEach { element ->
                        list.add(StageModel(element.name ?: "",
                                element.winner,
                                element.leader,
                                imgUrl = element.imgUrl))
                    }
                    list
                }
    }
}