package com.example.api;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TourScrappingIntegration {

    @Test
    public void getStagesEndpoint() throws IOException {
        TourScrappingService api = new ServiceFactory().build(TourScrappingService.class);
        if(api.getStages().execute().body()!=null){
            assertFalse(api.getStages().execute().body().isEmpty());
        } else {
            fail("Body is empty and it shouldn't main API endpoint");
        }
    }
}