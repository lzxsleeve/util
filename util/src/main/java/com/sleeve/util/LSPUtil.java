package com.sleeve.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 存储本地一些值的工具类
 * <p>
 * Create by liuzx on date 2019/7/12.
 */
public class LSPUtil {
    /**
     * 退出登录可以清除的数据
     */
    public static final String NAME_USER = "user";
    public static final String LOGIN_STATUS = "LoginStatus";

    private LSPUtil() {
    }

    public static LSPUtil getInstance() {
        return SingleHolder.INSTANCE;
    }

    private static class SingleHolder {
        private static final LSPUtil INSTANCE = new LSPUtil();
    }


    //----常用键值----start------------//

    /**
     * 获取登陆状态
     */
    public static boolean getLoginStatus() {
        return ((boolean) LSPUtil.getInstance().get(NAME_USER, LOGIN_STATUS, false));
    }

    public static void putLoginStatus(boolean phone) {
        LSPUtil.getInstance().put(NAME_USER, LOGIN_STATUS, phone);
    }

    //----常用键值----end------------//

    /**
     * 存储本地值
     *
     * @param name  文件名
     * @param key   对应的key
     * @param value 存储的值
     */
    public LSPUtil put(String name, String key, Object value) {
        if (value instanceof String) {
            LSPUtil.getPreferences(name).edit().putString(key, (String) value).apply();
        } else if (value instanceof Integer) {
            LSPUtil.getPreferences(name).edit().putInt(key, (Integer) value).apply();
        } else if (value instanceof Boolean) {
            LSPUtil.getPreferences(name).edit().putBoolean(key, (Boolean) value).apply();
        } else if (value instanceof Long) {
            LSPUtil.getPreferences(name).edit().putLong(key, (Long) value).apply();
        }
        return this;
    }

    public Object get(String name, String key, Object defValue) {
        if (defValue instanceof String) {
            return LSPUtil.getPreferences(name).getString(key, (String) defValue);
        } else if (defValue instanceof Integer) {
            return LSPUtil.getPreferences(name).getInt(key, (Integer) defValue);
        } else if (defValue instanceof Boolean) {
            return LSPUtil.getPreferences(name).getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof Long) {
            return LSPUtil.getPreferences(name).getLong(key, (Long) defValue);
        }
        return null;
    }

    /**
     * 删除某个key
     *
     * @param name 文件的名字
     * @param key  对应的 key
     * @return LSPUtil
     */
    public LSPUtil remove(String name, String key) {
        LSPUtil.getPreferences(name).edit().remove(key).apply();
        return this;
    }

    /**
     * 删除文件 name 下的所有 key
     *
     * @param name 文件的名字
     * @return LSPUtil
     */
    public LSPUtil clear(String name) {
        LSPUtil.getPreferences(name).edit().clear().apply();
        return this;
    }

    public static SharedPreferences getPreferences(String name) {
        return UtilConfig.mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
    }
}
