package com.sleeve.util;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 输入检查或限制
 * <p>
 * Create by lzx on 2020/05/26.
 */
public class LInputCheck {

    /**
     * @return if true 表示不为空
     */
    public static boolean noNull(TextView textView) {
        boolean b = TextUtils.isEmpty(textView.getText());
        if (b) {
            LToast.show(textView.getHint());
        }
        return !b;
    }

    /**
     * 限制输入小数点后两位
     * 不让他输入超过小数点后面两位
     */
    public static void limitPointTwo(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String num = s.toString();
                // 输入小数点的情况
                if (num.equals(".")) {
                    num = "0" + num;
                    editText.setText(num);
                    editText.setSelection(num.length());
                }

                int pointPos = num.indexOf(".");
                if (pointPos >= 0 && num.length() > (pointPos + 3)) {
                    String limitSrt;
                    // 输入一大串之后回到第一位输入小数点
                    if (pointPos == 0) {
                        num = "0" + num;
                        limitSrt = num.substring(0, pointPos + 4);
                    } else {
                        limitSrt = num.substring(0, pointPos + 3);
                    }

                    editText.setText(limitSrt);
                    editText.setSelection(limitSrt.length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    /**
     * 检查输入是否为 0
     */
    public static boolean isZero(TextView inputView, CharSequence toast) {
        if ("0".contentEquals(inputView.getText())) {
            LToast.show(toast + "不能为0");
            return false;
        } else {
            return true;
        }
    }
}
