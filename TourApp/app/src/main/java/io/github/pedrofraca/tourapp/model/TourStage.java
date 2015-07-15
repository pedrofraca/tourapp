package io.github.pedrofraca.tourapp.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TourStage implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(stagewinner);
        parcel.writeString(stageleader);
        parcel.writeString(name);
        parcel.writeString(km);
        parcel.writeString(imgUrl);
        parcel.writeString(date);
        parcel.writeSerializable(images);
        parcel.writeString(avgSpeed);
        parcel.writeString(startFinish);
        parcel.writeString(stage);
    }


    public static final Parcelable.Creator<TourStage> CREATOR
            = new Parcelable.Creator<TourStage>() {
        public TourStage createFromParcel(Parcel in) {
            return new TourStage(in);
        }

        public TourStage[] newArray(int size) {
            return new TourStage[size];
        }
    };

    private TourStage(Parcel in) {
        stagewinner = in.readString();
        stageleader = in.readString();
        name = in.readString();
        km = in.readString();
        imgUrl=in.readString();
        date=in.readString();
        images= (ArrayList<String>) in.readSerializable();
        avgSpeed=in.readString();
        startFinish=in.readString();
        stage=in.readString();
    }

}
