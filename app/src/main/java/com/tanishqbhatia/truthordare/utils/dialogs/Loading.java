package com.tanishqbhatia.truthordare.utils.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;

import com.tanishqbhatia.truthordare.R;
import com.tanishqbhatia.truthordare.utils.constants.ColorCons;

/**
 * Created by Tanishq Bhatia on 17-08-2017 at 10:02.
 * Email address : crash0er@gmail.com
 * Contact number : +919780702709
 */

public class Loading {
    private Dialog dialog;

    public Loading(Activity activity) {
        if (dialog == null)
            dialog = new Dialog(activity);
        else {
            dialog.dismiss();
            dialog = null;
        }
        if (!dialog.isShowing()) {
            dialog.setCancelable(false);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.loading);
            Window window = dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setBackgroundDrawable(new ColorDrawable(ColorCons.BLACK_95));
        }
    }

    public void show() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
