package io.github.pedrofraca.tourapp;

import java.util.List;

import io.github.pedrofraca.tourapp.model.TourStage;
import retrofit.http.GET;

/**
 * Created by pedrofraca on 08/07/15.
 */
public interface TourScrappingService {
    @GET("/tour")
    List<TourStage> getStages();
}
