package com.tanishqbhatia.truthordare.utils.toast;

import android.app.Activity;
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
    private Activity activity;
    public static final int HIGH_PRIORITY = ToastBuilder.PRIORITY_HIGH;
    public static final int NORMAL_PRIORITY = ToastBuilder.PRIORITY_NORMAL;
    public static final int LOW_PRIORITY = ToastBuilder.PRIORITY_LOW;
    public static final int LENGTH_SHORT = 3000;
    public static final int LENGTH_LONG = 5000;
    public static final int LENGTH_STICKY = -1;
    private int priority = NORMAL_PRIORITY;
    private int duration = LENGTH_SHORT;
    private int color = R.color.blue_500;

    private Toast(Activity activity) {
        this.activity = activity;
    }

    public static Toast priority(int priority) {
        if (toast == null)
            toast = new Toast(App.get().getCurrentActivity());
        toast.priority = priority;
        return toast;
    }

    public static Toast duration(int duration) {
        if (toast == null)
            toast = new Toast(App.get().getCurrentActivity());
        toast.duration = duration;
        return toast;
    }

    public static Toast color(int color) {
        if (toast == null)
            toast = new Toast(App.get().getCurrentActivity());
        toast.color = color;
        return toast;
    }

    public static ToastBuilder message(String... messages) {
        if (messages != null) {
            StringBuilder builder = new StringBuilder();
            for (String message : messages) {
                builder.append(message);
                builder.append(", ");
            }
            String message = builder.substring(0, builder.lastIndexOf(","));
            ToastBuilder.Style style = new ToastBuilder.Style(toast.duration, toast.color);
            ToastBuilder toastBuilder = ToastBuilder.makeText(toast.activity, message, style);
            toastBuilder.setLayoutGravity(Gravity.BOTTOM);
            toastBuilder.setPriority(toast.priority);
            return toastBuilder;
        } else return null;
    }
}
