package com.example.fina.fm_valid.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data_Track_S {
    @SerializedName("name")
    private String name;

    @SerializedName("duration")
    private String duration;

    @SerializedName("listeners")
    private String listeners;

    @SerializedName("mbid")
    private String mbid;

    @SerializedName("url")
    private String url;

    @SerializedName("streamable")
    private String streamable;

    @SerializedName("artist")
    private String artist;

    @SerializedName("image")
    private ArrayList<Data_Image> image;

    @SerializedName("@attr")
    private Data_Attr attr;

    private String ImageUrl ="";

    public String getImageUrl() {
        if (getImage() != null && getImage().size() > 0) {
            for (Data_Image img :
                    getImage()) {
                if (img.getSize().equalsIgnoreCase("large")) {
                    return img.getText();
                }
            }
        }
        return null;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public ArrayList<Data_Image> getImage() {
        return image;
    }

    public void setImage(ArrayList<Data_Image> image) {
        this.image = image;
    }

    public Data_Attr getAttr() {
        return attr;
    }

    public void setAttr(Data_Attr attr) {
        this.attr = attr;
    }
}
