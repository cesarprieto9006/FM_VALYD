package com.example.fina.fm_valid.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.fina.fm_valid.model.Data_Artist;
import com.example.fina.fm_valid.model.Data_Track;
import com.example.fina.fm_valid.model.Data_Track_S;

import java.util.ArrayList;


public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fm_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = SQLiteHelper.class.getSimpleName();

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLiteTables.DB_ARTIST);
        db.execSQL(SQLiteTables.DB_TRACK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SQLiteTables.DB_ARTIST+SQLiteTables.DB_TRACK+";");
        onCreate(db);
    }

    public boolean INSERT_DATA(String table, ContentValues values){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(table, null, values);
        if (result == -1){
            Log.d(TAG, "Error guardando");
            return false;
        }else{
            Log.d(TAG, "Guardo exitosamente");
            return true;
        }
    }

    public boolean Delete_DATA(String table){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table,"",null);
        if (result == -1){
            Log.d(TAG, "Error eliminando.");
            return false;
        }else{
            Log.d(TAG, "Elimino exitosamente.");
            return true;
        }
    }

    public ArrayList<Data_Artist> getArrayArtist(){
        Data_Artist artist;
        ArrayList<Data_Artist> dataArtistArrayList=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns={
                SQLiteTables.COL_ARTIST_ID,
                SQLiteTables.COL_ARTIST_MBID,
                SQLiteTables.COL_ARTIST_NAME,
                SQLiteTables.COL_ARTIST_IMAGE,
                SQLiteTables.COL_ARTIST_LISTENERS,
                SQLiteTables.COL_ARTIST_STREAMABLE,
                SQLiteTables.COL_ARTIST_URL,
                SQLiteTables.COL_ARTIST_IMAGE
        };

        Cursor cursor = db.query(SQLiteTables.TABLE_ARTIST, columns,
                "", null, null, null, null);

        while (cursor.moveToNext())
        {
            try
            {
                artist = new Data_Artist();
                artist.setMbid(cursor.getString(1));
                artist.setName(cursor.getString(2));
                artist.setListeners(cursor.getString(4));
                artist.setUrl(cursor.getString(6));
                artist.setStreamable(cursor.getString(5));
                artist.setImageUrl(cursor.getString(7));

                dataArtistArrayList.add(artist);
            }
            catch (Exception e) {
               e.printStackTrace();
            }

        }
        return dataArtistArrayList;
    }

    public ArrayList<Data_Artist> getArtistDetail(String name){
        Data_Artist artist;
        ArrayList<Data_Artist> dataArtistArrayList=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns={
                SQLiteTables.COL_ARTIST_ID,
                SQLiteTables.COL_ARTIST_MBID,
                SQLiteTables.COL_ARTIST_NAME,
                SQLiteTables.COL_ARTIST_IMAGE,
                SQLiteTables.COL_ARTIST_LISTENERS,
                SQLiteTables.COL_ARTIST_STREAMABLE,
                SQLiteTables.COL_ARTIST_URL,
                SQLiteTables.COL_ARTIST_IMAGE
        };

        String selection = SQLiteTables.COL_ARTIST_NAME +  " LIKE '%" + name + "%'" ;
        String[] args={name};

        Cursor cursor = db.query(SQLiteTables.TABLE_ARTIST, columns,
                selection, null, null, null, null);

        while (cursor.moveToNext())
        {
            try
            {
                artist = new Data_Artist();
                artist.setMbid(cursor.getString(1));
                artist.setName(cursor.getString(2));
                artist.setListeners(cursor.getString(4));
                artist.setUrl(cursor.getString(6));
                artist.setStreamable(cursor.getString(5));
                artist.setImageUrl(cursor.getString(7));
                dataArtistArrayList.add(artist);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dataArtistArrayList;
    }

    public ArrayList<Data_Track> getArrayTrack(){
        Data_Track dataTrack;
        ArrayList<Data_Track> dataTrackArrayList=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns={
                SQLiteTables.COL_TRACK_ID,
                SQLiteTables.COL_TRACK_MBID,
                SQLiteTables.COL_TRACK_NAME,
                SQLiteTables.COL_TRACK_IMAGE,
                SQLiteTables.COL_TRACK_LISTENERS,
                SQLiteTables.COL_TRACK_ARTIST,
                SQLiteTables.COL_TRACK_URL,
                SQLiteTables.COL_TRACK_DURATION
        };
        Cursor cursor = db.query(SQLiteTables.TABLE_TRACK, columns,
                "", null, null, null, null);

        while (cursor.moveToNext())
        {
            try
            {
                dataTrack = new Data_Track();
                dataTrack.setMbid(cursor.getString(1));
                dataTrack.setName(cursor.getString(2));
                dataTrack.setListeners(cursor.getString(4));
                dataTrack.setUrl(cursor.getString(6));
                dataTrack.setNameArtist(cursor.getString(5));
                dataTrack.setImageUrl(cursor.getString(3));
                dataTrack.setDuration(cursor.getString(7));

                dataTrackArrayList.add(dataTrack);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        return dataTrackArrayList;
    }

    public ArrayList<Data_Track_S> getArraySearchTrack(String name){
        Data_Track_S dataTrack;
        ArrayList<Data_Track_S> dataTrackArrayList=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns={
                SQLiteTables.COL_TRACK_ID,
                SQLiteTables.COL_TRACK_MBID,
                SQLiteTables.COL_TRACK_NAME,
                SQLiteTables.COL_TRACK_IMAGE,
                SQLiteTables.COL_TRACK_LISTENERS,
                SQLiteTables.COL_TRACK_ARTIST,
                SQLiteTables.COL_TRACK_URL,
                SQLiteTables.COL_TRACK_DURATION
        };

        String selection = SQLiteTables.COL_TRACK_NAME +  " LIKE '%" + name + "%'" ;
        String[] args={name};

        Cursor cursor = db.query(SQLiteTables.TABLE_TRACK, columns,
                selection, null, null, null, null);

        while (cursor.moveToNext())
        {
            try
            {
                dataTrack = new Data_Track_S();
                dataTrack.setMbid(cursor.getString(1));
                dataTrack.setName(cursor.getString(2));
                dataTrack.setListeners(cursor.getString(4));
                dataTrack.setUrl(cursor.getString(6));
                dataTrack.setArtist(cursor.getString(5));
                dataTrack.setImageUrl(cursor.getString(3));
                dataTrack.setDuration(cursor.getString(7));

                dataTrackArrayList.add(dataTrack);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        return dataTrackArrayList;
    }



}
