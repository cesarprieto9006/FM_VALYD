package com.example.fina.fm_valid.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data_Track_Matche {

    @SerializedName("track")
    private ArrayList<Data_Track_S> track;

    public ArrayList<Data_Track_S> getTrack() {
        return track;
    }

    public void setTrack(ArrayList<Data_Track_S> track) {
        this.track = track;
    }
}
