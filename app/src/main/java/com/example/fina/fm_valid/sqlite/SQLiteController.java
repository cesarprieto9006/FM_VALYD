package com.example.fina.fm_valid.sqlite;

import android.content.ContentValues;
import android.content.Context;


import com.example.fina.fm_valid.model.Data_Artist;

import java.util.ArrayList;

public class SQLiteController {
    private SQLiteHelper sqLiteHelper;
    Context context;

    public SQLiteController(Context mContext){
        this.context = mContext;
        sqLiteHelper = new SQLiteHelper(context);
    }

    public boolean SAVE_ARTIST_DATA(Data_Artist artist){

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

    public boolean DELETE_ARTIST_DATA(){
        return sqLiteHelper.Delete_DATA(SQLiteTables.TABLE_ARTIST);
    }

    public ArrayList<Data_Artist> getSearchArtistDetail(String name){
        return sqLiteHelper.getArtistDetail(name);
    }

    public ArrayList<Data_Artist> getArtistDetail(){
        return sqLiteHelper.getArrayArtist();
    }



    /*public boolean SAVE_TRACK_DATA(Track track){

        int num = (int) (Math.random() * 1000000) + 1;

        ContentValues values = new ContentValues();
        values.put(SQLiteTables.COL_TRACK_ID, num);
        values.put(SQLiteTables.COL_TRACK_MBID, track.getMbid());
        values.put(SQLiteTables.COL_TRACK_NAME, track.getName());
        values.put(SQLiteTables.COL_TRACK_PLAYCOUNT, track.getPlaycount());
        values.put(SQLiteTables.COL_TRACK_URL, track.getUrl());
        values.put(SQLiteTables.COL_TRACK_IMAGE, track.getImageUrl());
        values.put(SQLiteTables.COL_TRACK_DURATION, track.getDuration());
        values.put(SQLiteTables.COL_TRACK_ARTIST, String.valueOf(track.getArtist()));
        return sqLiteHelper.INSERT_DATA(SQLiteTables.TABLE_TRACK, values);
    }*/

    /*public Track getTrackDetail(String name){
        return sqLiteHelper.getTrackDetail(name);
    }*/
}
