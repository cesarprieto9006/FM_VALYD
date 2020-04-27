package com.example.fina.fm_valid.ui.Artist;


import com.example.fina.fm_valid.model.Data_Artist;
import com.example.fina.fm_valid.model.Data_Artist_Response;
import com.example.fina.fm_valid.model.Data_Search_Response;

import java.util.ArrayList;

public interface ArtistViewInterface {

    void showProgressBar();

    void hideProgressBar();

    void showLyPage();

    void hideLyPage();

    void showList();

    void hideList();

    void updatePageArtist(Data_Artist_Response dataResponse);

    void updatePageSearch(Data_Search_Response dataResponse);

    void displayError(String s);

    void stateArtist(ArrayList<Data_Artist> dataResponse);

    void getDatabase(String text);


}
