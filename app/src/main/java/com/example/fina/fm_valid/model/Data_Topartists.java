package com.example.fina.fm_valid.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data_Topartists {

    @SerializedName("artist")
    private ArrayList<Data_Artist>artist;

    @SerializedName("@attr")
    private Data_Attr attr;

    public ArrayList<Data_Artist> getArtist() {
        return artist;
    }

    public void setArtist(ArrayList<Data_Artist> artist) {
        this.artist = artist;
    }

    public Data_Attr getAttr() {
        return attr;
    }

    public void setAttr(Data_Attr attr) {
        this.attr = attr;
    }
}
