package com.tanishqbhatia.truthordare;

import android.app.Activity;
import android.app.Application;
import android.content.ContextWrapper;

import com.tanishqbhatia.truthordare.utils.Methods;
import com.tanishqbhatia.truthordare.utils.prefs.Prefs;

/**
 * Created by Tanishq Bhatia on 09-08-2017 at 17:03.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class App extends Application {
    private static App instance;
    private Activity currentActivity;

    public static synchronized App get() {
        if(instance == null) {
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
}
