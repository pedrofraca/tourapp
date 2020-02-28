package com.example.api.model;

import java.util.ArrayList;

/**
 * Created by pedrofraca on 15/07/15.
 */
public class TourStageClasification {
    ArrayList<Clasification> mountain;
    ArrayList<TeamClasification> team;
    ArrayList<Clasification> general;
    ArrayList<Clasification> regularity;
    ArrayList<Clasification> stage;

    public ArrayList<Clasification> getGeneral() {
        return general;
    }

    public ArrayList<Clasification> getMountain() {
        return mountain;
    }

    public ArrayList<Clasification> getRegularity() {
        return regularity;
    }

    public ArrayList<Clasification> getStage() {
        return stage;
    }

    public ArrayList<TeamClasification> getTeam() {
        return team;
    }
}
