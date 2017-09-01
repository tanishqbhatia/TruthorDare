package com.tanishqbhatia.truthordare.utils.constants;

import com.tanishqbhatia.truthordare.R;

/**
 * Created by Tanishq Bhatia on 10-08-2017 at 11:04.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class Cons {
    public static final int NAVIGATION_HOME = 0;
    public static final int NAVIGATION_SEARCH = 1;
    public static final int NAVIGATION_DASHBOARD = 2;
    public static final int NAVIGATION_NOTIFICATIONS = 3;
    public static final int NAVIGATION_USER = 4;
    public static final int NAVIGATION_TOTAL = 5;

    public static int getNavigationId(int id) {
        int resId = id;
        switch (resId) {
            case NAVIGATION_HOME:
                resId = R.id.navigation_home;
                break;
            case NAVIGATION_SEARCH:
                resId = R.id.navigation_search;
                break;
            case NAVIGATION_DASHBOARD:
                resId = R.id.navigation_dashboard;
                break;
            case NAVIGATION_NOTIFICATIONS:
                resId = R.id.navigation_notifications;
                break;
            case NAVIGATION_USER:
                resId = R.id.navigation_user;
                break;
        }
        return resId;
    }

    public static int getFragmentId(int itemId) {
        int id = itemId;
        switch (id) {
            case R.id.navigation_home:
                id = NAVIGATION_HOME;
                break;
            case R.id.navigation_search:
                id = NAVIGATION_SEARCH;
                break;
            case R.id.navigation_dashboard:
                id = NAVIGATION_DASHBOARD;
                break;
            case R.id.navigation_notifications:
                id = NAVIGATION_NOTIFICATIONS;
                break;
            case R.id.navigation_user:
                id = NAVIGATION_USER;
                break;
        }
        return id;
    }
}
