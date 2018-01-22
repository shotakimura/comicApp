package jp.co.atschool.comicapp;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;

/**
 * Created by shotakimura on 2018/01/14.
 */

public class BillingInfoBootstrap extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();
    }
}
