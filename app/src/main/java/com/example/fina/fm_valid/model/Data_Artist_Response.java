package com.example.fina.fm_valid.model;

import com.google.gson.annotations.SerializedName;

public class Data_Artist_Response {

    @SerializedName("topartists")
    private Data_Topartists topartists;

    public Data_Topartists getTopartists() {
        return topartists;
    }

    public void setTopartists(Data_Topartists topartists) {
        this.topartists = topartists;
    }

}
