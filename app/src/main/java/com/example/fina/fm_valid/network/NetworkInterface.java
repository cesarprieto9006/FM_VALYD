package com.example.fina.fm_valid.network;

import com.example.fina.fm_valid.model.Data_Artist_Response;
import com.example.fina.fm_valid.model.Data_SearchA_Response;
import com.example.fina.fm_valid.model.Data_SearchT_Response;
import com.example.fina.fm_valid.model.Data_Track_Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkInterface {

    @GET("?method=geo.gettopartists&format=json")
    Observable<Data_Artist_Response> getArtist(@Query("country") String Country, @Query("api_key") String ApiKey,
                                               @Query("limit") int limit, @Query("page") int page);

    @GET("http://ws.audioscrobbler.com/2.0/?method=artist.search&format=json")
    Observable<Data_SearchA_Response> geSearchArtist(@Query("artist") String Artist, @Query("api_key") String ApiKey,
                                                     @Query("limit") int limit, @Query("page") int page);

    @GET("?method=geo.getTopTracks&format=json")
    Observable<Data_Track_Response> getTrack(@Query("country") String Country, @Query("api_key") String ApiKey,
                                              @Query("limit") int limit, @Query("page") int page);

    @GET("http://ws.audioscrobbler.com/2.0/?method=track.search&format=json")
    Observable<Data_SearchT_Response> geSearchTrack(@Query("track") String track, @Query("api_key") String ApiKey,
                                                    @Query("limit") int limit, @Query("page") int page);


}
