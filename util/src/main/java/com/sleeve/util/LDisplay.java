package com.sleeve.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.TypedValue;
import android.view.WindowManager;

import androidx.annotation.ColorInt;

/**
 * 显示相关
 *
 * Create by liuzx on date 2019/7/12.
 */
public class LDisplay {

    /**
     * dp2px
     */
    public static int getDP(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                UtilConfig.mContext.getResources().getDisplayMetrics());
    }

    /**
     * sp2px
     */
    public static int sp2px(float spValue) {
        final float fontScale = UtilConfig.mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕宽度
     */
    public static int getDisplayWidth() {
        if (LAppInfo.getTargetSdkVersion() < 30) {
            return UtilConfig.mContext.getResources().getDisplayMetrics().widthPixels;
        }
        WindowManager windowManager = (WindowManager) UtilConfig.mContext.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            Rect rect = windowManager.getMaximumWindowMetrics().getBounds();
            return Math.abs(rect.width());
        } else {
            return -1;
        }
    }

    /**
     * 获取屏幕高度
     */
    public static int getDisplayHeight() {
        if (LAppInfo.getTargetSdkVersion() < 30) {
            return UtilConfig.mContext.getResources().getDisplayMetrics().heightPixels;
        }
        WindowManager windowManager = (WindowManager) UtilConfig.mContext.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            Rect rect = windowManager.getMaximumWindowMetrics().getBounds();
            return Math.abs(rect.height());
        } else {
            return -1;
        }
    }

    /**
     * 更改状态栏颜色
     *
     * @param activity
     * @param color
     */
    public static void setSimpleStatusBarColor(Activity activity, @ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().setStatusBarColor(color);
        }
    }

    /**
     * 屏幕分辨率
     * @return int[]{width, height}
     */
    public static int[] getScreenResolution() {
        if (LAppInfo.getTargetSdkVersion() < 30) {
            return new int[]{getDisplayWidth(), getDisplayHeight()};
        }
        WindowManager windowManager = (WindowManager) UtilConfig.mContext.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            Rect rect = windowManager.getMaximumWindowMetrics().getBounds();
            return new int[]{Math.abs(rect.width()), Math.abs(rect.height())};
        } else {
            return new int[]{0, 0};
        }
    }
}
