package com.sleeve.util;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import java.io.File;

/**
 * 获取App信息
 * <p>
 * Create by lzx on 2020/05/21.
 */
public class LAppInfo {

    /**
     * 获取当前程序版本号
     */
    public static long getVersionCode() {
        try {
            PackageInfo packageInfo = UtilConfig.mContext.getPackageManager().getPackageInfo(
                    UtilConfig.mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            return packageInfo.getLongVersionCode();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            LToast.show("获取版本号异常");
            return -1;
        }
    }

    /**
     * 获取当前程序版本名
     */
    public static String getVersionName() {
        try {
            PackageInfo packageInfo = UtilConfig.mContext.getPackageManager().getPackageInfo(
                    UtilConfig.mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            LToast.show("获取版本名异常");
            return "";
        }
    }

    /**
     * 获取当前程序包名
     */
    public static String getPackageName() {
        return UtilConfig.mContext.getPackageName();
    }

    /**
     * 是否安装了此 App
     */
    public static boolean hasInstallApk(String packageName) {
        PackageManager manager = UtilConfig.mContext.getPackageManager();
        try {
            ApplicationInfo info;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                info = manager.getApplicationInfo(packageName, PackageManager.MATCH_UNINSTALLED_PACKAGES);
            } else {
                info = manager.getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            }
            return info != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 打开 app
     *
     * @param packageName 包名
     */
    public static void openApp(String packageName) {
        try {
            Intent intent = UtilConfig.mContext.getPackageManager()
                    .getLaunchIntentForPackage(packageName);
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                UtilConfig.mContext.startActivity(intent);
            } else {
                LToast.show("未安装此应用");
            }
        } catch (Exception e) {
            LToast.show("未安装此应用");
        }
    }

    /**
     * 安装 apk
     */
    public static void installApk(File apkFile) {
        if (!apkFile.exists()) {
            LToast.show("文件不存在");
            return;
        }
        Intent intent = getIntent(apkFile);
        UtilConfig.mContext.startActivity(intent);
    }

    /**
     * file_provider 这个 uri 共享已经在升级库里面做了处理了
     * 所以直接拿来用就行了
     */
    public static Intent getIntent(File apkFile) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uri = FileProvider.getUriForFile(UtilConfig.mContext,
                    getPackageName() + ".file_provider", apkFile);
        } else {
            uri = Uri.fromFile(apkFile);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return intent;
    }
}
