package io.github.pedrofraca.data.datasource.model;

import androidx.annotation.Nullable;

public class Resource<T> {

    private Status mStatus;
    private T mData;
    private Throwable error;

    public Resource(Status status, T data) {
        mStatus = status;
        this.mData = data;
    }

    @Nullable
    public <T> T getData() {
        return (T) mData;
    }

    public Status getStatus(){
        return mStatus;
    }

    @Nullable
    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    public enum Status {
        ERROR,
        OK,
        CACHE
    }
}
