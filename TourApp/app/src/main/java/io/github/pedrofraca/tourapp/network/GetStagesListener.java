package io.github.pedrofraca.tourapp.network;

import java.util.List;

import io.github.pedrofraca.tourapp.model.TourStage;


public interface GetStagesListener {
    void onStagesReceived(List<TourStage> stages);
    void onStagesError(Exception error);
}