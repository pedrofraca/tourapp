package com.example.data.model;

/**
 * UI model exported by the data layer.
 */
public class Stage {

    private String mName;
    private String mKm;
    private String mWinner;
    private String mLeader;
    private boolean mCompleted;

    public Stage (String name, String km, String winner, String leader, boolean completed) {
        mName = name;
        mKm = km;
        mWinner = winner;
        mLeader = leader;
        mCompleted = completed;
    }

    public String getName() {
        return mName;
    }

    public String getKm() {
        return mKm;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public String getWinner() {
        return mWinner;
    }

    public String getLeader() {
        return mLeader;
    }
}
