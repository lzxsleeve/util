package com.sleeve.util;

import java.nio.ByteBuffer;

/**
 * 格式转换
 *
 * Create by lzx on date 2019/3/6.
 */
public class LUnitChangeUtil {

    /**
     * 将byte数组转成
     *
     * @param src
     * @return
     */
    public static float byteArrayToFloat(byte[] src) {
        ByteBuffer sb = ByteBuffer.allocate(4);
        sb.put(src);  //放入byte数组
        float s = sb.getFloat(0);  //按short取出
        return s;
    }

    /**
     * @param h1
     * @param h2
     * @param l3
     * @param l4
     * @return
     */
    public static float byteAppendToFloat(byte h1, byte h2, byte l3, byte l4) {
        ByteBuffer sb = ByteBuffer.allocate(4);
        sb.put(new byte[]{h1, h2, l3, l4});  //放入byte数组
        float s = sb.getFloat(0);  //按float取出
        return s;
    }


    /**
     * 将float转成2个byte
     *
     * @param f
     * @return
     */
    public static byte[] floatToByteArray(float f) {
        ByteBuffer buf = ByteBuffer.allocate(4);
        buf.putFloat(f);
        return buf.array();
    }

    /**
     * 将short转成2个byte
     *
     * @param f
     * @return
     */
    public static byte[] shortToByteArray(short f) {
        ByteBuffer buf = ByteBuffer.allocate(2);
        buf.putShort(f);
        return buf.array();
    }

    /**
     * 将2个byte 合并成一个short
     *
     * @param high short 前8位(高位)
     * @param low  short 后8位(低位)
     * @return sort (high_low)
     */
    public static short byteAppendToShort(byte high, byte low) {
        ByteBuffer sb = ByteBuffer.allocate(2);
        sb.put(new byte[]{high, low});  //放入两个byte
        short s = sb.getShort(0);  //按short取出
        return s;
    }

    /**
     * 字节数组转成16进制表示格式的字符串
     *
     * @param byteArray 需要转换的字节数组
     * @return 16进制表示格式的字符串
     **/
    public static String bytesToHexString(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException("this byteArray must not be null or empty");

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }

    /**
     * 字节数组转成16进制表示格式的字符串
     *
     * @param byteArray 需要转换的字节数组
     * @return 16进制表示格式的字符串
     **/
    public static String bytesToHexStringWithSpace(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException("this byteArray must not be null or empty");

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
            hexString.append(" ");
        }
        return hexString.toString().toLowerCase();
    }

    public static float shortToHexByte() {
        return 0;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


}
