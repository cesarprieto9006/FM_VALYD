package com.example.fina.fm_valid.ui.Artist;


import android.util.Log;

import com.example.fina.fm_valid.model.Data_Artist_Response;
import com.example.fina.fm_valid.model.Data_Search_Response;
import com.example.fina.fm_valid.network.NetworkClient;
import com.example.fina.fm_valid.network.NetworkInterface;

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

public class ArtistPresenter implements ArtistPresenterInterface {

    private ArtistViewInterface artistViewInterface;
    private String TAG = "LoginPresenter";


    public ArtistPresenter(ArtistViewInterface artistViewInterface) {
        this.artistViewInterface = artistViewInterface;
    }

    @Override
    public void getArtist(String Country, String Key, int Limit, int Page) {
        artistViewInterface.showProgressBar();
        artistViewInterface.hideList();
        getObservableArtist(Country,Key,Limit,Page).subscribeWith(getObserverArtist());
    }

    @Override
    public void getSearchArtist(String Name, String Key, int Limit, int Page) {
        artistViewInterface.showProgressBar();
        artistViewInterface.hideList();
        getObservableSearchArtist(Name,Key,Limit,Page).subscribeWith(getObserverSearchArtist());
    }

    public Observable<Data_Artist_Response> getObservableArtist(String Country, String Key, int Limit, int Page) {
        Observable<Data_Artist_Response> getObservable;

        getObservable= NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getArtist(Country,Key,Limit,Page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return getObservable;
    }

    public Observable<Data_Search_Response> getObservableSearchArtist(String Name, String Key, int Limit, int Page) {
        Observable<Data_Search_Response> getObservable;

        getObservable= NetworkClient.getRetrofit().create(NetworkInterface.class)
                .geSearchArtist(Name,Key,Limit,Page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return getObservable;
    }

    public DisposableObserver<Data_Artist_Response> getObserverArtist() {
        return new DisposableObserver<Data_Artist_Response>() {

            @Override
            public void onNext(@NonNull Data_Artist_Response dataResponseObject) {
                Log.d(TAG, "OnNext");

                artistViewInterface.hideProgressBar();
                artistViewInterface.showList();
                artistViewInterface.stateArtist(dataResponseObject.getTopartists().getArtist());
                artistViewInterface.updatePageArtist(dataResponseObject);

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

                artistViewInterface.hideProgressBar();
                artistViewInterface.displayError(errMsg);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Completed");
            }
        };
    }

    public DisposableObserver<Data_Search_Response> getObserverSearchArtist() {
        return new DisposableObserver<Data_Search_Response>() {

            @Override
            public void onNext(@NonNull Data_Search_Response dataResponseObject) {
                Log.d(TAG, "OnNext");

                artistViewInterface.hideProgressBar();
                artistViewInterface.showList();
                artistViewInterface.stateArtist(dataResponseObject.getResults().getArtistmatches().getArtist());
                artistViewInterface.updatePageSearch(dataResponseObject);

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

                artistViewInterface.hideProgressBar();
                artistViewInterface.displayError(errMsg);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Completed");
            }
        };
    }


}
