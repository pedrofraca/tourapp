package io.github.pedrofraca.data.datasource.classification

import io.github.pedrofraca.data.datasource.ReadOnlyDataSourceWithParam
import io.github.pedrofraca.data.datasource.WriteDataSource
import io.github.pedrofraca.domain.model.StageClassificationModel

class ClassificationRepositoryImpl(private val api: ReadOnlyDataSourceWithParam<StageClassificationModel, String>,
                                   private val db: WriteDataSource<StageClassificationModel>?) : ClassificationRepository {

    override fun getClassificationForStage(stage: String) : StageClassificationModel {
        val classification = api.get(stage)
        db?.save(classification)
        return classification
    }

}
