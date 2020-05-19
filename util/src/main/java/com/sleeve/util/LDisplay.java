package com.sleeve.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
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
        WindowManager windowManager = (WindowManager) UtilConfig.mContext.getSystemService(
                Context.WINDOW_SERVICE);
        if (windowManager != null) {
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            return point.x;
        } else {
            return -1;
        }
    }

    /**
     * 获取屏幕高度
     */
    public static int getDisplayHeight() {
        WindowManager windowManager = (WindowManager) UtilConfig.mContext.getSystemService(
                Context.WINDOW_SERVICE);
        if (windowManager != null) {
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            return point.y;
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
        WindowManager wm = (WindowManager) (UtilConfig.mContext.getSystemService(Context.WINDOW_SERVICE));
        Point point = new Point();
        wm.getDefaultDisplay().getRealSize(point);
        int width = point.x;
        int height = point.y;
        return new int[]{width, height};

    }
}
