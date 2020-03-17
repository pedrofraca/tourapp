package io.github.pedrofraca.data.datasource.api

import io.github.pedrofraca.data.datasource.ReadOnlyDataSource
import io.github.pedrofraca.domain.model.StageModel
import io.github.pedrofraca.tour.api.ServiceFactory
import io.github.pedrofraca.tour.api.TourScrappingService
import io.reactivex.Observable

/**
 * Data source connecting to the API and mapping the result to the domain
 */
class StagesApiDataSource(private val apiService: TourScrappingService = ServiceFactory().build(TourScrappingService::class.java)) : ReadOnlyDataSource<StageModel> {
    override fun get(): Observable<List<StageModel>> {
        return apiService.stages()
                .toObservable()
                .map {
                    val list = ArrayList<StageModel>()
                    it.forEach { element ->
                        list.add(StageModel(element?.name ?: "",
                                winner = element?.winner,
                                imgUrl = element?.images?.get(0),
                                leader = element?.leader,
                                km = element?.km))
                    }
                    list
                }
    }
}