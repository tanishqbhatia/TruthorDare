package com.tanishqbhatia.truthordare.utils.constants;

import com.tanishqbhatia.truthordare.App;
import com.tanishqbhatia.truthordare.R;

/**
 * Created by Tanishq Bhatia on 16-08-2017 at 11:47.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class AppCons {
    public static final String APP_NAME = App.get().getResources().getString(R.string.app_name);
    public static final String APP_DESCRIPTION = App.get().getResources().getString(R.string.app_description);
    public static final int APP_LOGO = R.mipmap.ic_launcher;

    public static final String TRUTH = App.get().getResources().getString(R.string.truth);
    public static final String TRUTH_DESCRIPTION = App.get().getResources().getString(R.string.truth_description);
    public static final int TRUTH_LOGO = R.mipmap.ic_launcher;

    public static final String DARE = App.get().getResources().getString(R.string.dare);
    public static final String DARE_DESCRIPTION = App.get().getResources().getString(R.string.dare_description);
    public static final int DARE_LOGO = R.mipmap.ic_launcher;

}
