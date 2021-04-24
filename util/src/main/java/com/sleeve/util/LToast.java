package com.sleeve.util;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

/**
 * Toast工具类
 *
 * Create by liuzx on date 2019/7/11.
 */
public class LToast {
    private static Toast mToast;

    public static void show(CharSequence str) {
        if (initToast() != null) {
            TextView tv = mToast.getView().findViewById(R.id.toast_massage);
            tv.setText(str);
            mToast.show();
        }
    }

    public static void show(@StringRes int resId) {
        if (initToast() != null) {
            TextView tv = mToast.getView().findViewById(R.id.toast_massage);
            tv.setText(resId);
            mToast.show();
        }
    }

    @Nullable
    private static Toast initToast() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        Application application = UtilConfig.mContext;
        if (application == null) {
            return null;
        }
        mToast = new Toast(application);
        View view = LayoutInflater.from(application).inflate(R.layout.toast_layout, null);
        mToast.setView(view);
        mToast.setDuration(Toast.LENGTH_SHORT);
        return mToast;
    }
}
