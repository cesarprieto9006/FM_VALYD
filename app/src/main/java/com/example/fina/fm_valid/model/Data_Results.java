package com.example.fina.fm_valid.model;

import com.google.gson.annotations.SerializedName;

public class Data_Results {

    @SerializedName("opensearch:Query")
    private Data_Query Query;

    @SerializedName("opensearch:totalResults")
    private String totalResults;

    @SerializedName("opensearch:startIndex")
    private String startIndex;

    @SerializedName("opensearch:itemsPerPage")
    private String itemsPerPage;

    @SerializedName("artistmatches")
    private Data_Artist_Matche artistmatches;

    @SerializedName("trackmatches")
    private Data_Track_Matche trackmatches;

    @SerializedName("@attr")
    private Data_Attr attr;

    public Data_Track_Matche getTrackmatches() {
        return trackmatches;
    }

    public void setTrackmatches(Data_Track_Matche trackmatches) {
        this.trackmatches = trackmatches;
    }

    public Data_Query getQuery() {
        return Query;
    }

    public void setQuery(Data_Query query) {
        Query = query;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(String startIndex) {
        this.startIndex = startIndex;
    }

    public String getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(String itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public Data_Artist_Matche getArtistmatches() {
        return artistmatches;
    }

    public void setArtistmatches(Data_Artist_Matche artistmatches) {
        this.artistmatches = artistmatches;
    }

    public Data_Attr getAttr() {
        return attr;
    }

    public void setAttr(Data_Attr attr) {
        this.attr = attr;
    }
}
