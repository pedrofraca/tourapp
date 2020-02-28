package com.example.api;

import java.util.List;

import com.example.api.model.TourStage;
import com.example.api.model.TourStageClasification;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pedrofraca on 08/07/15.
 */
public interface TourScrappingService {
    @GET("/tour")
    Call<List<TourStage>> getStages();
    @GET("/clasification")
    Call<TourStageClasification> getClasificationForStage(@Query("stage") String stage);
}
