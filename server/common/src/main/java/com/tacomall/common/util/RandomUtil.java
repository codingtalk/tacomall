package com.tacomall.common.util;

public class RandomUtil {

    public static String createBigSmallLetterStrOrNumberRadom(int num) {
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= num; i++) {
            int intVal = (int) (Math.random() * 58 + 65);
            if (intVal >= 91 && intVal <= 96) {
                i--;
            }
            if (intVal < 91 || intVal > 96) {
                if (intVal % 2 == 0) {
                    str.append((char) intVal);
                } else {
                    str.append((int) (Math.random() * 10));
                }
                if (i % 4 == 0) {
                    str.append("-");
                }
            }
        }
        return str.substring(0, str.length() - 1);
    }

    public static String createSmallStrOrNumberRadom(int num) {
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= num; i++) {
            int intVal = (int) (Math.random() * 26 + 97);
            if (intVal % 2 == 0) {
                str.append((char) intVal);
            } else {
                str.append((int) (Math.random() * 10));
            }
            if (i % 4 == 0) {
                str.append("-");
            }
        }
        return str.substring(0, str.length() - 1);
    }

    public static String createBigStrOrNumberRadom(int num) {
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= num; i++) {
            int intVal = (int) (Math.random() * 26 + 65);
            if (intVal % 2 == 0) {
                str.append((char) intVal);
            } else {
                str.append((int) (Math.random() * 10));
            }
            if (i % 4 == 0) {
                str.append("-");
            }
        }
        return str.substring(0, str.length() - 1);
    }
}
