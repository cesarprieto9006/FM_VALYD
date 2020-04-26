package com.example.fina.fm_valid.model;

import com.google.gson.annotations.SerializedName;

public class Data_Image {

    @SerializedName("#text")
    private String text="";

    @SerializedName("size")
    private String size="";

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
