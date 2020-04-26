package com.example.fina.fm_valid;

import android.app.Application;

import com.example.fina.fm_valid.di.components.ApplicationComponent;
import com.example.fina.fm_valid.di.components.DaggerApplicationComponent;
import com.example.fina.fm_valid.di.modules.ApplicationModule;

public class MyApplication extends Application {

    private static ApplicationComponent applicationComponent;

      public MyApplication(){

      }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                                .builder()
                                .applicationModule(new ApplicationModule(this))
                                .build();

    }

    public ApplicationComponent getApplicationComponent(){
          return applicationComponent;
      }

}
