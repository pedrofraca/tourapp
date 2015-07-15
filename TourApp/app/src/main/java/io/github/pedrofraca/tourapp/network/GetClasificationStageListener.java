package io.github.pedrofraca.tourapp.network;

import io.github.pedrofraca.tourapp.model.TourStageClasification;


public interface GetClasificationStageListener {
    void onClasificationReceived(TourStageClasification clasification);
    void onClasificationError(Exception error);
}
