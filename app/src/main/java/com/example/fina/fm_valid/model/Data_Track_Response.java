package com.example.fina.fm_valid.model;

import com.google.gson.annotations.SerializedName;

public class Data_Track_Response {
    @SerializedName("tracks")
    private Data_Tracks tracks;

    public Data_Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Data_Tracks tracks) {
        this.tracks = tracks;
    }
}
