package com.sleeve.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类
 * <p>
 * Create by liuzx on date 2019/7/15.
 */
public class LThrowableUtil {

    /**
     * Throwable转String
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter stringWriter = new StringWriter();

        try (PrintWriter printWriter = new PrintWriter(stringWriter)) {
            throwable.printStackTrace(printWriter);
            return stringWriter.toString();
        }
    }
}
