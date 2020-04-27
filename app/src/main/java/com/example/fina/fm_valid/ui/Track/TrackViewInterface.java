package com.example.fina.fm_valid.ui.Track;


import com.example.fina.fm_valid.model.Data_Artist;
import com.example.fina.fm_valid.model.Data_Artist_Response;
import com.example.fina.fm_valid.model.Data_SearchA_Response;
import com.example.fina.fm_valid.model.Data_SearchT_Response;
import com.example.fina.fm_valid.model.Data_Track;
import com.example.fina.fm_valid.model.Data_Track_Response;
import com.example.fina.fm_valid.model.Data_Track_S;

import java.util.ArrayList;

public interface TrackViewInterface {

    void showProgressBar();

    void hideProgressBar();

    void showLyPage();

    void hideLyPage();

    void showList();

    void hideList();

    void updatePageTrack(Data_Track_Response dataResponse);

    void updatePageSearch(Data_SearchT_Response dataResponse);

    void displayError(String s);

    void stateTrack(ArrayList<Data_Track> dataResponse);

    void stateTrackS(ArrayList<Data_Track_S> dataResponse);

    void getDatabase(String text);


}
