package io.github.pedrofraca.tourapp.model;


import com.google.gson.annotations.SerializedName;

public class TourStage {
    @SerializedName("stage-winner") private String stagewinner;
    @SerializedName("stage-leader") private String stageleader;
    private String name;
    private String km;
    private String imgUrl;
    private String date;

    public String getDescription() {
        return name;
    }

    public String getKm() {
        return km;
    }

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

    public boolean completed(){
        return !stagewinner.isEmpty();
    }
}
