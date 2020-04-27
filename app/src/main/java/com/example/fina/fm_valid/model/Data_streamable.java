package com.example.fina.fm_valid.model;

import com.google.gson.annotations.SerializedName;

class Data_streamable {
    @SerializedName("#text")
    private String text;

    @SerializedName("fulltrack")
    private String fulltrack;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFulltrack() {
        return fulltrack;
    }

    public void setFulltrack(String fulltrack) {
        this.fulltrack = fulltrack;
    }
}
