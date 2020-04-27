package com.example.fina.fm_valid.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data_Tracks {
    @SerializedName("track")
    private ArrayList<Data_Track> tracks;

    @SerializedName("@attr")
    private Data_Attr attr;

    public ArrayList<Data_Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Data_Track> tracks) {
        this.tracks = tracks;
    }

    public Data_Attr getAttr() {
        return attr;
    }

    public void setAttr(Data_Attr attr) {
        this.attr = attr;
    }
}
