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

    public static final String APP_NAME = App.get().getResources().getString(R.string.app_name);
    public static final String APP_DESCRIPTION = App.get().getResources().getString(R.string.app_description);
    public static final int APP_LOGO = R.mipmap.ic_launcher;
    public static final int GREY_50 = ContextCompat.getColor(App.get(), R.color.grey_50);
    public static final int GREY_500 = ContextCompat.getColor(App.get(), R.color.grey_500);
    public static final int GREY_700 = ContextCompat.getColor(App.get(), R.color.grey_700);
    public static final int BLACK = ContextCompat.getColor(App.get(), R.color.black);

    public static final int NAVIGATION_HOME = 0;
    public static final int NAVIGATION_SEARCH = 1;
    public static final int NAVIGATION_DASHBOARD = 2;
    public static final int NAVIGATION_NOTIFICATIONS = 3;
    public static final int NAVIGATION_PROFILE = 4;
    public static final int NAVIGATION_TOTAL = 5;

    static final String IDENTIFIED = "identified";
}
