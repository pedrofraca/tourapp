package io.github.pedrofraca.tourapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pedrofraca on 15/07/15.
 */
public class TeamClasification  implements Parcelable {
    String time;
    String pos;
    String team;

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pos);
        parcel.writeString(time);
        parcel.writeString(team);
    }


    public static final Parcelable.Creator<TeamClasification> CREATOR
            = new Parcelable.Creator<TeamClasification>() {
        public TeamClasification createFromParcel(Parcel in) {
            return new TeamClasification(in);
        }

        public TeamClasification[] newArray(int size) {
            return new TeamClasification[size];
        }
    };

    private TeamClasification(Parcel in) {
        pos = in.readString();
        time = in.readString();
        team = in.readString();
    }

    @Override
    public String toString() {
        return pos + " - " + time + " " + team;
    }
}
