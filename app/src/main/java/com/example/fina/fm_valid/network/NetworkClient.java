package com.example.fina.fm_valid.network;

import com.example.fina.fm_valid.utils.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by anujgupta on 26/12/17.
 */

public class NetworkClient {

    public static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                        .baseUrl(Constant.Url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(okHttpClient)
                        .build();
        }
        return retrofit;
    }
}
