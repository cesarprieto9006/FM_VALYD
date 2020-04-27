package com.example.fina.fm_valid.model;

import com.google.gson.annotations.SerializedName;

public class Data_Attr {

    @SerializedName("country")
    private String country="";

    @SerializedName("page")
    private String page="";

    @SerializedName("perPage")
    private String perPage="";

    @SerializedName("totalPages")
    private String totalPages="";

    @SerializedName("total")
    private String total="";

    @SerializedName("for")
    private String For="";

    @SerializedName("rank")
    private String rank="";

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getFor() {
        return For;
    }

    public void setFor(String aFor) {
        For = aFor;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPerPage() {
        return perPage;
    }

    public void setPerPage(String perPage) {
        this.perPage = perPage;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
