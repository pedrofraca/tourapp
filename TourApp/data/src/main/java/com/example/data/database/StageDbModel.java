package com.example.data.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "stage")
public class StageDbModel {
    @PrimaryKey
    @NonNull
    private String name;
    private String kms;
    private String winner;
    private String leader;
    private boolean isCompleted;

    public String getName() {
        return name;
    }

    public String getKms() {
        return kms;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setKms(@NonNull String kms) {
        this.kms = kms;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getLeader() {
        return leader;
    }
}
