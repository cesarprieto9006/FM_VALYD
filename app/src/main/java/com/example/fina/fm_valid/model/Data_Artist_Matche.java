package com.example.fina.fm_valid.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data_Artist_Matche {

    @SerializedName("artist")
    private ArrayList<Data_Artist> artist;

    public ArrayList<Data_Artist> getArtist() {
        return artist;
    }

    public void setArtist(ArrayList<Data_Artist> artist) {
        this.artist = artist;
    }
}
