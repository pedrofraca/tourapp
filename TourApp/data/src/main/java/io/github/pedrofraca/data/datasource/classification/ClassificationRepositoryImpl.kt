package io.github.pedrofraca.data.datasource.classification

import io.github.pedrofraca.data.datasource.ReadOnlyDataSourceWithParam
import io.github.pedrofraca.data.datasource.WriteDataSourceSingleWithParams
import io.github.pedrofraca.domain.model.StageClassificationModel

class ClassificationRepositoryImpl(private val api: ReadOnlyDataSourceWithParam<StageClassificationModel, String>,
                                   private val db: WriteDataSourceSingleWithParams<StageClassificationModel, String>) : ClassificationRepository {

    override fun refreshForStage(stage: String) : StageClassificationModel {
        val classification = api.get(stage)
        db.save(classification)
        return classification
    }

    override fun getClassificationForStage(stage: String): StageClassificationModel {
        return db.get(stage)
    }

}
