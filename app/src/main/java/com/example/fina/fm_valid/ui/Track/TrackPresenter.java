package com.example.fina.fm_valid.ui.Track;


import android.util.Log;

import com.example.fina.fm_valid.model.Data_Artist_Response;
import com.example.fina.fm_valid.model.Data_SearchA_Response;
import com.example.fina.fm_valid.model.Data_SearchT_Response;
import com.example.fina.fm_valid.model.Data_Track;
import com.example.fina.fm_valid.model.Data_Track_Response;
import com.example.fina.fm_valid.network.NetworkClient;
import com.example.fina.fm_valid.network.NetworkInterface;
import com.example.fina.fm_valid.ui.Artist.ArtistPresenterInterface;
import com.example.fina.fm_valid.ui.Artist.ArtistViewInterface;

import org.json.JSONObject;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class TrackPresenter implements TrackPresenterInterface {

    private TrackViewInterface trackViewInterface;
    private String TAG = "Track";
    private String Text1="";


    public TrackPresenter(TrackViewInterface trackViewInterface) {
        this.trackViewInterface = trackViewInterface;
    }

    @Override
    public void getTrack(String Country, String Key, int Limit, int Page) {
        trackViewInterface.showProgressBar();
        trackViewInterface.hideList();
        trackViewInterface.hideLyPage();
        getObservableTrack(Country,Key,Limit,Page).subscribeWith(getObserverArtist());
    }

    @Override
    public void getSearchArtist(String Name, String Key, int Limit, int Page) {
        Text1=Name;
        trackViewInterface.showProgressBar();
        trackViewInterface.hideList();
        trackViewInterface.hideLyPage();
        getObservableSearchArtist(Name,Key,Limit,Page).subscribeWith(getObserverSearchArtist());
    }

    public Observable<Data_Track_Response> getObservableTrack(String Country, String Key, int Limit, int Page) {
        Observable<Data_Track_Response> getObservable;

        getObservable= NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getTrack(Country,Key,Limit,Page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return getObservable;
    }

    public Observable<Data_SearchT_Response> getObservableSearchArtist(String Name, String Key, int Limit, int Page) {
        Observable<Data_SearchT_Response> getObservable;

        getObservable= NetworkClient.getRetrofit().create(NetworkInterface.class)
                .geSearchTrack(Name,Key,Limit,Page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return getObservable;
    }

    public DisposableObserver<Data_Track_Response> getObserverArtist() {
        return new DisposableObserver<Data_Track_Response>() {

            @Override
            public void onNext(@NonNull Data_Track_Response dataResponseObject) {
                Log.d(TAG, "OnNext");

                trackViewInterface.hideProgressBar();
                trackViewInterface.showList();
                trackViewInterface.showLyPage();
                trackViewInterface.stateTrack(dataResponseObject.getTracks().getTracks());
                trackViewInterface.updatePageTrack(dataResponseObject);

            }

            @Override
            public void onError(@NonNull Throwable e) {

                String errMsg = "";

                try {
                    if (e instanceof SocketTimeoutException) {
                        //paymentViewInterface.statePaymentError(dataObjecterror);
                        errMsg="Timeout";
                    }
                    if(e instanceof UnknownHostException){
                        errMsg="Verifique la red.";
                    } else {
                        ResponseBody response = ((HttpException) e).response().errorBody();
                        JSONObject json = null;
                        json = new JSONObject(new String(response.bytes()));
                    }
                } catch (Exception error) {
                    error.printStackTrace();
                }

                trackViewInterface.getDatabase("");
                trackViewInterface.showList();
                trackViewInterface.hideProgressBar();
                trackViewInterface.displayError(errMsg);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Completed");
            }
        };
    }

    public DisposableObserver<Data_SearchT_Response> getObserverSearchArtist() {
        return new DisposableObserver<Data_SearchT_Response>() {

            @Override
            public void onNext(@NonNull Data_SearchT_Response dataResponseObject) {
                Log.d(TAG, "OnNext");

                trackViewInterface.showLyPage();
                trackViewInterface.hideProgressBar();
                trackViewInterface.showList();
                trackViewInterface.stateTrackS(dataResponseObject.getResults().getTrackmatches().getTrack());
                trackViewInterface.updatePageSearch(dataResponseObject);
                trackViewInterface.showLyPage();

            }

            @Override
            public void onError(@NonNull Throwable e) {

                String errMsg = "";

                try {
                    if (e instanceof SocketTimeoutException) {
                        //paymentViewInterface.statePaymentError(dataObjecterror);
                        errMsg="Timeout";
                    }
                    if(e instanceof UnknownHostException){
                        errMsg="Verifique la red.";
                    } else {
                        ResponseBody response = ((HttpException) e).response().errorBody();
                        JSONObject json = null;
                        json = new JSONObject(new String(response.bytes()));
                    }
                } catch (Exception error) {
                    error.printStackTrace();
                }
                trackViewInterface.getDatabase(Text1);
                trackViewInterface.showList();
                trackViewInterface.hideProgressBar();
                trackViewInterface.displayError(errMsg);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Completed");
            }
        };
    }


}
