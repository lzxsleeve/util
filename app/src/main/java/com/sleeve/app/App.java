package com.sleeve.app;

import android.app.Application;

import com.sleeve.util.UtilConfig;

/**
 * 说明
 * <p>
 * Create by Sleeve on date 2021/4/8.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        UtilConfig.init(this);
    }
}
