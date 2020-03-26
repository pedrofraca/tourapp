package io.github.pedrofraca.tour.api

import io.github.pedrofraca.tour.api.model.Stage
import io.github.pedrofraca.tour.api.model.StageClassification
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TourScrappingService {
    @GET("/tour")
    fun stages(): Single<List<Stage?>>

    @GET("/clasification")
    fun getClasificationForStage(@Query("stage") stage: String?): Single<StageClassification>
}