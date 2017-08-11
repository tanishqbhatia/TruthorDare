package com.tanishqbhatia.truthordare.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tanishqbhatia.truthordare.App;
import com.tanishqbhatia.truthordare.activities.IntroductionActivity;

import butterknife.ButterKnife;

/**
 * Created by Tanishq Bhatia on 10-08-2017 at 10:41.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class Methods {
    public static void showLog(String TAG, String... messages) {
        StringBuilder builder = new StringBuilder();
        for (String message : messages) {
            builder.append(message);
            builder.append(", ");
        }
        Log.i(TAG, builder.toString());
    }

    public static void showToast(String... messages) {
        StringBuilder builder = new StringBuilder();
        for (String message : messages) {
            builder.append(message);
            builder.append(", ");
        }
        Toast.makeText(App.get(), builder.toString(), Toast.LENGTH_SHORT).show();
    }

    public static Context init(Activity activity) {
        App.get().setCurrentActivity(activity);
        ButterKnife.bind(activity);
        return activity;
    }

    public static void visible(View view) {
        if(view != null) {
            if(view.getVisibility() != View.VISIBLE) view.setVisibility(View.VISIBLE);
        }
    }

    public static void invisible(View view) {
        if(view != null) {
            if(view.getVisibility() != View.INVISIBLE) view.setVisibility(View.INVISIBLE);
        }
    }

    public static void gone(View view) {
        if(view != null) {
            if(view.getVisibility() != View.GONE) view.setVisibility(View.GONE);
        }
    }

    public static void cleanSlateProtocol() {
        Prefs.clear();
        Intent intent = new Intent(App.get(), IntroductionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.get().startActivity(intent);
    }
}
