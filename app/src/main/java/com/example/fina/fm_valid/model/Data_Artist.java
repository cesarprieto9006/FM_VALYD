package com.example.fina.fm_valid.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data_Artist {

    @SerializedName("name")
    private String name="";

    @SerializedName("listeners")
    private String listeners="";

    @SerializedName("mbid")
    private String mbid="";

    @SerializedName("url")
    private String url="";

    @SerializedName("streamable")
    private String streamable="";

    @SerializedName("image")
    private ArrayList<Data_Image> image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public ArrayList<Data_Image> getImage() {
        return image;
    }

    public void setImage(ArrayList<Data_Image> image) {
        this.image = image;
    }
}