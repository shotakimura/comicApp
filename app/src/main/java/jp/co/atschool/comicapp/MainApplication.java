package jp.co.atschool.comicapp;

import android.app.Application;

import com.beardedhen.androidbootstrap.TypefaceProvider;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by shotakimura on 2018/01/22.
 */

public class MainApplication extends Application {

    /**
     * アプリケーションの最初に呼ばれる
     *
     * @since 1.0.0
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //bootstrap
        TypefaceProvider.registerDefaultIconSets();

        //Realm
        Realm.init(this);

        //timber
        if (BuildConfig.DEBUG) {
            // デバック時のみ Timberでログ出力
            Timber.plant(new Timber.DebugTree());
        } else {
            // 商用利用時は Crashlyticsにクラッシュレポートを送るように設定
//            Fabric.with(this, new Crashlytics());  // ここはとりあえず不要
        }
    }
}