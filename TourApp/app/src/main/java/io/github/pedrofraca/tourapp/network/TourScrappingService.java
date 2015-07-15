package io.github.pedrofraca.tourapp.network;

import java.util.List;

import io.github.pedrofraca.tourapp.model.TourStage;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by pedrofraca on 08/07/15.
 */
public interface TourScrappingService {
    @GET("/tour")
    List<TourStage> getStages();
    @GET("/clasification")
    List<TourStage> getStages(@Query("stage") String sort);
}
