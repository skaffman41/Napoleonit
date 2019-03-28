package com.skaffman.napoleonit;

import android.app.Application;

import com.skaffman.napoleonit.api.Api;
import com.skaffman.napoleonit.api.ApiInitializer;

public class App extends Application {

    public static Api api;

    @Override
    public void onCreate() {
        super.onCreate();

        api = new ApiInitializer().init();
    }
}
