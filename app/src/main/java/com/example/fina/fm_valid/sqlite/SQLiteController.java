package com.example.fina.fm_valid.sqlite;

import android.content.ContentValues;
import android.content.Context;


import com.example.fina.fm_valid.model.Data_Artist;
import com.example.fina.fm_valid.model.Data_Track;
import com.example.fina.fm_valid.model.Data_Track_S;

import java.util.ArrayList;

public class SQLiteController {
    private SQLiteHelper sqLiteHelper;
    Context context;

    public SQLiteController(Context mContext){
        this.context = mContext;
        sqLiteHelper = new SQLiteHelper(context);
    }

    public boolean Save_Artist(Data_Artist artist){

        int num = (int) (Math.random() * 1000000) + 1;

        ContentValues values = new ContentValues();
        values.put(SQLiteTables.COL_ARTIST_ID, num);
        values.put(SQLiteTables.COL_ARTIST_MBID, artist.getMbid());
        values.put(SQLiteTables.COL_ARTIST_NAME, artist.getName());
        values.put(SQLiteTables.COL_ARTIST_LISTENERS, artist.getListeners());
        values.put(SQLiteTables.COL_ARTIST_URL, artist.getUrl());
        values.put(SQLiteTables.COL_ARTIST_IMAGE, artist.getImageUrl());
        values.put(SQLiteTables.COL_ARTIST_STREAMABLE, artist.getStreamable());
        return sqLiteHelper.INSERT_DATA(SQLiteTables.TABLE_ARTIST, values);
    }

    public boolean Delete_Artist(){
        return sqLiteHelper.Delete_DATA(SQLiteTables.TABLE_ARTIST);
    }

    public ArrayList<Data_Artist> getSearchArtistDetail(String name){
        return sqLiteHelper.getArtistDetail(name);
    }

    public ArrayList<Data_Artist> getArtistDetail(){
        return sqLiteHelper.getArrayArtist();
    }


    public boolean Delete_Track(){
        return sqLiteHelper.Delete_DATA(SQLiteTables.TABLE_TRACK);
    }

    public boolean Save_Track(Data_Track dataTrack){

        int num = (int) (Math.random() * 1000000) + 1;

        ContentValues values = new ContentValues();
        values.put(SQLiteTables.COL_TRACK_ID, num);
        values.put(SQLiteTables.COL_TRACK_MBID, dataTrack.getMbid());
        values.put(SQLiteTables.COL_TRACK_NAME, dataTrack.getName());
        values.put(SQLiteTables.COL_TRACK_LISTENERS, dataTrack.getListeners());
        values.put(SQLiteTables.COL_TRACK_URL, dataTrack.getUrl());
        values.put(SQLiteTables.COL_TRACK_IMAGE, dataTrack.getImageUrl());
        values.put(SQLiteTables.COL_TRACK_DURATION, dataTrack.getDuration());
        values.put(SQLiteTables.COL_TRACK_ARTIST, String.valueOf(dataTrack.getNameArtist()));
        return sqLiteHelper.INSERT_DATA(SQLiteTables.TABLE_TRACK, values);
    }

    public boolean Save_Track_Search(Data_Track_S dataTrack){

        int num = (int) (Math.random() * 1000000) + 1;

        ContentValues values = new ContentValues();
        values.put(SQLiteTables.COL_TRACK_ID, num);
        values.put(SQLiteTables.COL_TRACK_MBID, dataTrack.getMbid());
        values.put(SQLiteTables.COL_TRACK_NAME, dataTrack.getName());
        values.put(SQLiteTables.COL_TRACK_LISTENERS, dataTrack.getListeners());
        values.put(SQLiteTables.COL_TRACK_URL, dataTrack.getUrl());
        values.put(SQLiteTables.COL_TRACK_IMAGE, dataTrack.getImageUrl());
        values.put(SQLiteTables.COL_TRACK_DURATION, dataTrack.getDuration());
        values.put(SQLiteTables.COL_TRACK_ARTIST, String.valueOf(dataTrack.getArtist()));
        return sqLiteHelper.INSERT_DATA(SQLiteTables.TABLE_TRACK, values);
    }

    public ArrayList<Data_Track> getTrackDetail(){
        return sqLiteHelper.getArrayTrack();
    }

    public ArrayList<Data_Track_S> getTrackSearchDetail(String text){
        return sqLiteHelper.getArraySearchTrack(text);
    }
}
