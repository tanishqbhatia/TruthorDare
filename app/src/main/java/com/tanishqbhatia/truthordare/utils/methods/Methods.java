package com.tanishqbhatia.truthordare.utils.methods;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import com.tanishqbhatia.truthordare.App;
import com.tanishqbhatia.truthordare.R;
import com.tanishqbhatia.truthordare.activities.IntroductionActivity;
import com.tanishqbhatia.truthordare.utils.prefs.Prefs;

import java.io.UnsupportedEncodingException;

import butterknife.ButterKnife;
import butterknife.Unbinder;

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

    public static Unbinder init(Fragment fragment, View view) {
        App.get().setCurrentFragment(fragment);
        return ButterKnife.bind(fragment, view);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(App.get().getCurrentActivity());
        builder.setCancelable(false).setMessage(R.string.notIdentified)
                .setPositiveButton(R.string.okay, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Prefs.clear();
                        launchOnly(IntroductionActivity.class);
                    }
                }).create().show();
    }

    public static void launch(Class targetClass) {
        App.get().getCurrentActivity().startActivity(new Intent(App.get().getCurrentActivity(), targetClass));
    }

    public static void launchOnly(Class targetClass) {
        Intent intent = new Intent(App.get().getCurrentActivity(), targetClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        App.get().getCurrentActivity().startActivity(intent);
        App.get().getCurrentActivity().finish();
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

    public static String encode(String text) {
        byte[] data = new byte[0];
        try {
            data = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return android.util.Base64.encodeToString(data, android.util.Base64.DEFAULT);
    }

    public static String decode(String text) {
        byte[] data = android.util.Base64.decode(text, android.util.Base64.DEFAULT);
        try {
            text = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }
}
