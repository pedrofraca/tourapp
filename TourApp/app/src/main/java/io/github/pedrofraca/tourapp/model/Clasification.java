package io.github.pedrofraca.tourapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pedrofraca on 15/07/15.
 */
public class Clasification implements Parcelable {
    String time;
    String country;
    String team;
    String pos;
    String rider;

    @Override
    public String toString() {
        return pos + " - " + rider +" ("+country+") "+ time +" "+  team;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pos);
        parcel.writeString(rider);
        parcel.writeString(time);
        parcel.writeString(team);
        parcel.writeString(country);
    }


    public static final Parcelable.Creator<Clasification> CREATOR
            = new Parcelable.Creator<Clasification>() {
        public Clasification createFromParcel(Parcel in) {
            return new Clasification(in);
        }

        public Clasification[] newArray(int size) {
            return new Clasification[size];
        }
    };

    private Clasification(Parcel in) {
        pos = in.readString();
        rider = in.readString();
        time = in.readString();
        team = in.readString();
        country=in.readString();
    }
}
