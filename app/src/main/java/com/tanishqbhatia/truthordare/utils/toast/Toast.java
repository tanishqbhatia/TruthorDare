package com.tanishqbhatia.truthordare.utils.toast;

import android.view.Gravity;

import com.tanishqbhatia.truthordare.App;
import com.tanishqbhatia.truthordare.R;


/**
 * Created by Tanishq Bhatia on 14-08-2017 at 10:42.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class Toast {
    private static Toast toast;
    //private static Activity activity;
    public static final int HIGH_PRIORITY = ToastBuilder.PRIORITY_HIGH;
    public static final int NORMAL_PRIORITY = ToastBuilder.PRIORITY_NORMAL;
    public static final int LOW_PRIORITY = ToastBuilder.PRIORITY_LOW;
    public static final int LENGTH_SHORT = 3000;
    public static final int LENGTH_LONG = 5000;
    public static final int LENGTH_STICKY = -1;
    private static int priority = NORMAL_PRIORITY;
    private static int duration = LENGTH_SHORT;
    private static int color = R.color.blue_500;

    /*private Toast(Activity toast_activity) {
        activity = toast_activity;
    }*/

    public static Toast priority(int activity_priority) {
        priority = activity_priority;
        return toast;
    }

    public static Toast duration(int activity_duration) {
        duration = activity_duration;
        return toast;
    }

    public static Toast color(int activity_color) {
        color = activity_color;
        return toast;
    }

    public static ToastBuilder message(String... messages) {
/*        if (activity != App.get().getCurrentActivity())
            toast = new Toast(App.get().getCurrentActivity());*/
        if (messages != null) {
            StringBuilder builder = new StringBuilder();
            for (String message : messages) {
                builder.append(message);
                builder.append("\n");
            }
            String message = builder.substring(0, builder.lastIndexOf("\n"));
            ToastBuilder.Style style = new ToastBuilder.Style(duration, color);
            ToastBuilder toastBuilder = ToastBuilder.makeText(App.get().getCurrentActivity(), message, style);
            toastBuilder.setLayoutGravity(Gravity.BOTTOM);
            toastBuilder.setPriority(priority);
            return toastBuilder;
        } else return null;
    }
}