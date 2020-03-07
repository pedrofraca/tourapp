package io.github.pedrofraca.data.datasource.api

import io.github.pedrofraca.data.datasource.StageDataSource
import io.github.pedrofraca.domain.model.StageModel
import io.github.pedrofraca.tour.api.ServiceFactory
import io.github.pedrofraca.tour.api.TourScrappingService
import io.github.pedrofraca.tour.api.model.Stage
import io.reactivex.rxjava3.core.Single

class StagesApiDatasource(private val apiService : TourScrappingService = ServiceFactory().build(TourScrappingService::class.java)) : StageDataSource {
    override fun get(stage: String): Single<List<StageModel>> {
       return apiService.stages()
               .toObservable()
               .flatMapIterable { items -> items }
               .map {StageModel(it?.winner) }
               .toList()
    }

}