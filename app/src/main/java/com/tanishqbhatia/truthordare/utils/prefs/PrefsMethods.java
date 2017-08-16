package com.tanishqbhatia.truthordare.utils.prefs;

import com.tanishqbhatia.truthordare.utils.constants.PrefsCons;

/**
 * Created by Tanishq Bhatia on 10-08-2017 at 10:40.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class PrefsMethods {
    public static void setIdentified() {
        Prefs.putBoolean(PrefsCons.IDENTIFIED, true);
    }

    public static void setNotIdentified() {
        Prefs.putBoolean(PrefsCons.IDENTIFIED, false);
    }

    public static Boolean isIdentified() {
        return Prefs.getBoolean(PrefsCons.IDENTIFIED, false);
    }


    public static void saveAccessToken(String accessToken) {
        Prefs.putString(PrefsCons.ACCESS_TOKEN, accessToken);
    }

    public static void removeAccessToken() {
        Prefs.remove(PrefsCons.ACCESS_TOKEN);
    }

    public static String getAccessToken() {
        return Prefs.getString(PrefsCons.ACCESS_TOKEN, "");
    }
}
