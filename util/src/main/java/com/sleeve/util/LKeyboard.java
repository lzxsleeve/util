package com.sleeve.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;

/**
 * 软键盘相关
 *
 * Create by liuzx on date 2019/7/12.
 */

public class LKeyboard {
    /**
     * 隐藏软键盘
     */
    public static void hideSoftInput(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) UtilConfig.mContext
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     * 显示软键盘
     */
    public static void showSoftInput(final View view) {
        InputMethodManager imm = (InputMethodManager) UtilConfig.mContext.
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null || view == null) return;
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 系统输入法的bug 15<=sdk<=23内存泄漏
     */
    public static void fixInputMethodManagerLeak(Context context) {
        if (context == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field field;
        Object obj;
        for (String param : arr) {
            try {
                field = imm.getClass().getDeclaredField(param);
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                obj = field.get(imm);
                if (obj != null && obj instanceof View) {
                    View vGet = (View) obj;
                    if (vGet.getContext() == context) {
                        field.set(imm, null);
                    } else {
                        break;
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
