package io.github.pedrofraca.tourapp.framework.datasource

import io.github.pedrofraca.data.datasource.ReadOnlyDataSource
import io.github.pedrofraca.domain.model.StageModel
import io.github.pedrofraca.tour.api.ServiceFactory
import io.github.pedrofraca.tour.api.TourScrappingService

/**
 * Data source connecting to the API and mapping the result to the domain
 */
class StagesApiDataSource(private val apiService: TourScrappingService = ServiceFactory().build(TourScrappingService::class.java)) : ReadOnlyDataSource<StageModel> {
    override fun get(): List<StageModel> {
        return apiService.stages().blockingGet()
                .map {
                    StageModel(it?.name ?: "",
                            winner = it?.winner,
                            imgUrl = it?.images?.get(0),
                            leader = it?.leader,
                            km = it?.km,
                            averageSpeed = it?.averageSpeed,
                            stage = it?.stage?:"")
                }
    }
}