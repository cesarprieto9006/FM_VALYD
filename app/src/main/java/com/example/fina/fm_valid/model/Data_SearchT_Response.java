package com.example.fina.fm_valid.model;

import com.google.gson.annotations.SerializedName;

public class Data_SearchT_Response {

    @SerializedName("results")
    private Data_Results results;

    public Data_Results getResults() {
        return results;
    }

    public void setResults(Data_Results results) {
        this.results = results;
    }
}
