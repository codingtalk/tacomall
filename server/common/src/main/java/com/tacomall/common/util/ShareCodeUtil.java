package com.tacomall.common.util;

import java.util.Random;

public final class ShareCodeUtil {

    private static final char[] r = new char[] { 'Q', 'W', 'e', '8', 'A', 's', '2', 'D', 'z', 'X', '9', 'C', '7', 'p',
            '5', 'i', 'K', '3', 'M', 'J', 'u', 'F', 'r', '4', 'V', 'y', 'l', 'T', 'n', '6', 'B', 'g', 'h' };

    private static final char b = 'o';

    private static final int binLen = r.length;

    private static final int s = 6;

    public static String toSerialCode(long id) {
        char[] buf = new char[32];
        int charPos = 32;

        while ((id / binLen) > 0) {
            int ind = (int) (id % binLen);
            buf[--charPos] = r[ind];
            id /= binLen;
        }
        buf[--charPos] = r[(int) (id % binLen)];
        String str = new String(buf, charPos, (32 - charPos));
        if (str.length() < s) {
            StringBuilder sb = new StringBuilder();
            sb.append(b);
            Random rnd = new Random();
            for (int i = 1; i < s - str.length(); i++) {
                sb.append(r[rnd.nextInt(binLen)]);
            }
            str += sb.toString();
        }
        return str;
    }

    public static long codeToId(String code) {
        char chs[] = code.toCharArray();
        long res = 0L;
        for (int i = 0; i < chs.length; i++) {
            int ind = 0;
            for (int j = 0; j < binLen; j++) {
                if (chs[i] == r[j]) {
                    ind = j;
                    break;
                }
            }
            if (chs[i] == b) {
                break;
            }
            if (i > 0) {
                res = res * binLen + ind;
            } else {
                res = ind;
            }
        }
        return res;
    }
}
