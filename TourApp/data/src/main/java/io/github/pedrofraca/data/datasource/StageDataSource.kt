package io.github.pedrofraca.data.datasource

import io.github.pedrofraca.domain.model.StageModel
import io.reactivex.rxjava3.core.Single

interface StageDataSource {
    fun get(stage: String): Single<List<StageModel>>
}