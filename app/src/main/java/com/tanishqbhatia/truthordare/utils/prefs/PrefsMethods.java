package com.tanishqbhatia.truthordare.utils.prefs;

import com.tanishqbhatia.truthordare.utils.constants.PrefsCons;

/**
 * Created by Tanishq Bhatia on 10-08-2017 at 10:40.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class PrefsMethods {
    public static void setIndentified() {
        Prefs.putBoolean(PrefsCons.IDENTIFIED, true);
    }

    public static Boolean isIdentified() {
        return Prefs.getBoolean(PrefsCons.IDENTIFIED, false);
    }
}
