package com.example.fina.fm_valid.ui.Artist;

public interface ArtistPresenterInterface {

    void getArtist(String Country,String Key,int Limit,int Page);
    void getSearchArtist(String Artist,String Key,int Limit,int Page);
}
