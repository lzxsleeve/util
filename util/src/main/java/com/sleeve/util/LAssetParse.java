package com.sleeve.util;

import android.content.res.AssetManager;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

import java.io.IOException;
import java.io.InputStream;

/**
 * 解析assets 里面的文件
 * <p>
 * Create by liuzx on date 2019/7/12.
 */
public class LAssetParse {

    /**
     * 获取asstes
     *
     * @param fileName assets 文件夹里面的文件名字
     */
    @Nullable
    @WorkerThread
    public static String getFileString(String fileName) {
        AssetManager assets = UtilConfig.mContext.getAssets();
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = assets.open(fileName)) {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            String json = new String(bytes, "UTF-8");
            stringBuilder.append(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return stringBuilder.toString();
    }

    /**
     * @param fileName    assets 文件夹里面的文件名字
     * @param charsetName charset名字
     */
    @Nullable
    @WorkerThread
    public static String getFileString(String fileName, String charsetName) {
        AssetManager assets = UtilConfig.mContext.getAssets();
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = assets.open(fileName)) {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            String json = new String(bytes, charsetName);
            stringBuilder.append(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return stringBuilder.toString();
    }
}
