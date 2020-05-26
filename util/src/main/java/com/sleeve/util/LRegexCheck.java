package com.sleeve.util;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * 常用正则判断
 * <p>
 * Create by lzx on 2020/05/26.
 */
public class LRegexCheck {

    private static final String REGEX_PHONE = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(16[6])|(17[0,1,3,5-8])|(18[0-9])|(19[8,9]))\\d{8}$";
    private static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static final String REGEX_ID_CARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";
    private static final String LETTER = "[a-zA-Z]";

    /**
     * 判断是否是手机号码
     */
    public static boolean isPhoneNum(CharSequence sequence) {
        return !TextUtils.isEmpty(sequence) && Pattern.matches(REGEX_PHONE, sequence);
    }

    /**
     * 判断是否是邮箱地址
     */
    public static boolean isEmail(CharSequence sequence) {
        return !TextUtils.isEmpty(sequence) && Pattern.matches(REGEX_EMAIL, sequence);
    }

    /**
     * 判断是否是身份证卡号
     */
    public static boolean isIDCNum(CharSequence sequence) {
        return !TextUtils.isEmpty(sequence) && Pattern.matches(REGEX_ID_CARD18, sequence);
    }

    /**
     * 判断是否存在字母
     */
    public static boolean hasLetter(CharSequence sequence) {
        Pattern pattern = Pattern.compile(LETTER);
        return pattern.matcher(sequence).find();
    }
}
