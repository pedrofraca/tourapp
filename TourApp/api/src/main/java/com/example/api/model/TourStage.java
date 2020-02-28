package com.example.api.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TourStage {
    @SerializedName("stage-winner") private String stagewinner;
    @SerializedName("stage-leader") private String stageleader;
    @SerializedName("stage-images") private ArrayList<String> images;
    private String name;
    private String km;
    private String imgUrl;
    private String date;
    private String stage;
    @SerializedName("avg-speed") private String avgSpeed;
    @SerializedName("start-finish") private String startFinish;

    public String getDescription() {
        return name;
    }

    public String getAverageSpeed(){
        return avgSpeed;
    }

    public String getStartFinish(){
        return startFinish;
    }

    public String getKm() {
        return km;
    }
    public String getStage(){return stage;}

    public String getWinner() {
        return stagewinner;
    }
    public String getLeader() {
        return stageleader;

    }
    public String getDate() {
        return date;
    }
    public String getImgUrl() {
        return imgUrl;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public boolean completed(){
        return !stagewinner.isEmpty();
    }
}
