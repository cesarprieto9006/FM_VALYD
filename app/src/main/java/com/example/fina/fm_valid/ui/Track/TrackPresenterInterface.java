package com.example.fina.fm_valid.ui.Track;

public interface TrackPresenterInterface {

    void getTrack(String Country, String Key, int Limit, int Page);
    void getSearchArtist(String Artist, String Key, int Limit, int Page);
}
