package io.github.pedrofraca.tourapp.network;

import java.util.List;

import io.github.pedrofraca.tourapp.model.TourStage;
import io.github.pedrofraca.tourapp.model.TourStageClasification;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by pedrofraca on 08/07/15.
 */
public interface TourScrappingService {
    @GET("/tour")
    List<TourStage> getStages();
    @GET("/clasification")
    TourStageClasification getClasificationForStage(@Query("stage") String stage);
}
