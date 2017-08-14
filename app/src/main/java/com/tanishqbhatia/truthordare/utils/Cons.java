package com.tanishqbhatia.truthordare.utils;

import android.support.v4.content.ContextCompat;

import com.tanishqbhatia.truthordare.App;
import com.tanishqbhatia.truthordare.R;

/**
 * Created by Tanishq Bhatia on 10-08-2017 at 11:04.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class Cons {
    public final String WEBSITE_URL = "";
    public final String CHECK_SERVER = "";

    public final String INSTAGRAM_CLIENT_ID = App.get().getResources().getString(R.string.instagram_client_id);
    public final String INSTAGRAM_CLIENT_SECRET = App.get().getResources().getString(R.string.instagram_client_secret);
    public final String INSTAGRAM_CALLBACK_URL = App.get().getResources().getString(R.string.instagram_callback_url);

    public static final String APP_NAME = App.get().getResources().getString(R.string.app_name);
    public static final String APP_DESCRIPTION = App.get().getResources().getString(R.string.app_description);
    public static final int APP_LOGO = R.mipmap.ic_launcher;

    public static final String TRUTH = App.get().getResources().getString(R.string.truth);
    public static final String TRUTH_DESCRIPTION = App.get().getResources().getString(R.string.truth_description);
    public static final int TRUTH_LOGO = R.mipmap.ic_launcher;

    public static final String DARE = App.get().getResources().getString(R.string.dare);
    public static final String DARE_DESCRIPTION = App.get().getResources().getString(R.string.dare_description);
    public static final int DARE_LOGO = R.mipmap.ic_launcher;

    public static final int GREY_50 = ContextCompat.getColor(App.get(), R.color.grey_50);
    public static final int GREY_500 = ContextCompat.getColor(App.get(), R.color.grey_500);
    public static final int GREY_700 = ContextCompat.getColor(App.get(), R.color.grey_700);
    public static final int GREY_900 = ContextCompat.getColor(App.get(), R.color.grey_900);

    public static final int BLACK = ContextCompat.getColor(App.get(), R.color.black);
    public static final int WHITE = ContextCompat.getColor(App.get(), R.color.white);

    public static final int BLUE_500 = ContextCompat.getColor(App.get(), R.color.blue_500);

    public static final int RED_500 = ContextCompat.getColor(App.get(), R.color.red_500);
    public static final int RED_900 = ContextCompat.getColor(App.get(), R.color.red_900);

    public static final int GREEN_500 = ContextCompat.getColor(App.get(), R.color.green_500);
    public static final int GREEN_900 = ContextCompat.getColor(App.get(), R.color.green_900);

    public static final int NAVIGATION_HOME = 0;
    public static final int NAVIGATION_SEARCH = 1;
    public static final int NAVIGATION_DASHBOARD = 2;
    public static final int NAVIGATION_NOTIFICATIONS = 3;
    public static final int NAVIGATION_PROFILE = 4;
    public static final int NAVIGATION_TOTAL = 5;

    static final String IDENTIFIED = "identified";
}
