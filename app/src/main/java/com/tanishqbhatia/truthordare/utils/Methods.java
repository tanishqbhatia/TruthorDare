package com.tanishqbhatia.truthordare.utils;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;

import com.tanishqbhatia.truthordare.App;
import com.tanishqbhatia.truthordare.activities.IntroductionActivity;
import com.tanishqbhatia.truthordare.utils.prefs.Prefs;

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
            builder.append("\n");
        }
        String message = builder.substring(0, builder.lastIndexOf("\n"));
        Log.i(TAG, message);
    }

    public static Context init(Activity activity) {
        App.get().setCurrentActivity(activity);
        ButterKnife.bind(activity);
        return activity;
    }

    public static void visible(View view) {
        if (view != null) {
            if (view.getVisibility() != View.VISIBLE) view.setVisibility(View.VISIBLE);
        }
    }

    public static void invisible(View view) {
        if (view != null) {
            if (view.getVisibility() != View.INVISIBLE) view.setVisibility(View.INVISIBLE);
        }
    }

    public static void gone(View view) {
        if (view != null) {
            if (view.getVisibility() != View.GONE) view.setVisibility(View.GONE);
        }
    }

    public static void enable(View view) {
        if (view != null) {
            if (!view.isEnabled()) view.setEnabled(true);
        }
    }

    public static void disable(View view) {
        if (view != null) {
            if (view.isEnabled()) view.setEnabled(false);
        }
    }

    public static void cleanSlateProtocol() {
/*        AlertDialog.Builder builder = new AlertDialog.Builder(App.get());
        builder.setCancelable(false).setMessage(R.string.notIdentified)
                .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Prefs.clear();
                        Intent intent = new Intent(App.get(), IntroductionActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        App.get().startActivity(intent);
                    }
                }).create().show();*/

        Prefs.clear();
        Intent intent = new Intent(App.get(), IntroductionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        App.get().startActivity(intent);
    }

    public static void launch(Activity sourceActivity, Class targetClass) {
        sourceActivity.startActivity(new Intent(sourceActivity, targetClass));
    }

    public static void changeBackgroundColor(final View view, int targetColor, int duration) {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), ((ColorDrawable)view.getBackground()).getColor(), targetColor);
        colorAnimation.setDuration(duration);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                view.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();
    }
}
