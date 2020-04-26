package com.example.fina.fm_valid.model;

import com.google.gson.annotations.SerializedName;

public class Data_Query {

    @SerializedName("#text")
    private String text;

    @SerializedName("role")
    private String role;

    @SerializedName("searchTerms")
    private String searchTerms;

    @SerializedName("startPage")
    private String startPage;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSearchTerms() {
        return searchTerms;
    }

    public void setSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    public String getStartPage() {
        return startPage;
    }

    public void setStartPage(String startPage) {
        this.startPage = startPage;
    }
}
