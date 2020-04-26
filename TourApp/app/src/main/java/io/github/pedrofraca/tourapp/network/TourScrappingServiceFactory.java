package io.github.pedrofraca.tourapp.network;

import io.github.pedrofraca.tourapp.network.TourScrappingService;
import retrofit.RestAdapter;

/**
 * Created by pedrofraca on 08/07/15.
 */
public class TourScrappingServiceFactory {

    TourScrappingService build(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://tourscraping.appspot.com")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();


        return restAdapter.create(TourScrappingService.class);
    }
}