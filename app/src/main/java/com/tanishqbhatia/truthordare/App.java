package com.tanishqbhatia.truthordare;

import android.app.Activity;
import android.app.Application;
import android.content.ContextWrapper;
import android.support.v4.app.Fragment;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tanishqbhatia.truthordare.interfaces.Server;
import com.tanishqbhatia.truthordare.utils.constants.WebsiteCons;
import com.tanishqbhatia.truthordare.utils.methods.Methods;
import com.tanishqbhatia.truthordare.utils.prefs.Prefs;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tanishq Bhatia on 09-08-2017 at 17:03.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class App extends Application {
    private static App instance;
    private Activity currentActivity;
    private Fragment currentFragment;
    private Retrofit retrofit;
    private Server server;

    public static synchronized App get() {
        if (instance == null) {
            instance = new App();
            Methods.showLog("App", "get()", "Initialized instance.");
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initPrefs();
        initRetrofit();
        initServer();
        initFresco();
    }

    private void initFresco() {
        Fresco.initialize(instance);
    }

    private void initRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(WebsiteCons.WEBSITE_URL_ORIGINAL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    private void initServer() {
        if(retrofit == null)
            initRetrofit();
        if (server == null)
            server = retrofit.create(Server.class);
    }

    public Server getServer() {
        if (server == null)
            initServer();
        return server;
    }

    private void initPrefs() {
        new Prefs.Builder()
                .setContext(instance)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
        Methods.showLog("App", "initPrefs()", "Built prefs.");
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(Fragment currentFragment) {
        this.currentFragment = currentFragment;
    }
}
