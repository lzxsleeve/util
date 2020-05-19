package com.sleeve.util;

import android.app.Application;

/**
 * 需要在 application 初始化 {@link #init(Application)}
 *
 * Create by liuzx on date 2019/7/11.
 */
public class UtilConfig {

    public static Application mContext;

    public static void init(Application context) {
        mContext = context;
    }
}
