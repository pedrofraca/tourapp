package io.github.pedrofraca.tour.api

import io.github.pedrofraca.tour.api.model.Stage
import io.github.pedrofraca.tour.api.model.StageClassification
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TourScrappingService {
    @GET("/api/stage")
    fun stages(): Single<List<Stage?>>

    @GET("/api/stage/{stage}/classification")
    fun getClassificationForStage(@Path("stage") stage: String?): Single<StageClassification>
}