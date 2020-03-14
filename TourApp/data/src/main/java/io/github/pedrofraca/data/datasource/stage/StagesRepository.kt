package io.github.pedrofraca.data.datasource.stage

import androidx.lifecycle.LiveData
import io.github.pedrofraca.data.datasource.model.Resource
import io.github.pedrofraca.domain.model.StageModel

interface StagesRepository {
    val stages: LiveData<Resource<List<StageModel>>>
}