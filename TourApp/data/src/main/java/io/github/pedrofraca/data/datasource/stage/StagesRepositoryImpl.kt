package io.github.pedrofraca.data.datasource.stage

import io.github.pedrofraca.data.datasource.ReadOnlyDataSource
import io.github.pedrofraca.data.datasource.WriteDataSource
import io.github.pedrofraca.domain.model.StageModel

class StagesRepositoryImplementation(private val apiDataSource: ReadOnlyDataSource<StageModel>,
                                     private val databaseDataSource: WriteDataSource<StageModel>) : StagesRepository {

    fun refresh() {
        apiDataSource.get().forEach {
            databaseDataSource.save(it)
        }
    }

    override val stages: List<StageModel>
        get() = databaseDataSource.get()
}