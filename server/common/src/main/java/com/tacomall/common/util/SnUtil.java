package com.tacomall.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class SnUtil {

    private static final int TYPE_ORDER_FORM = 1;
    private static final int TYPE_PAY_FORM = 2;
    private static final int TYPE_REFUND_FORM = 3;
    private static final int TYPE_APPROVE = 4;

    private static final String CODE_ORDER_FORM = "OF";
    private static final String CODE_PAY_FORM = "PF";
    private static final String CODE_REFUND_FORM = "RF";
    private static final String CODE_APPROVE = "RF";

    private String dateStr() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return LocalDateTime.now().format(fmt);
    }

    private static long getRandomStr(long n) {
        long min = 1, max = 9;
        for (int i = 1; i < n; i++) {
            min *= 10;
            max *= 10;
        }
        long rangeLong = (((long) (new Random().nextDouble() * (max - min)))) + min;
        return rangeLong;
    }

    public String gen(int type) {
        switch (type) {
            case TYPE_ORDER_FORM:
                return CODE_ORDER_FORM + "-" + dateStr() + getRandomStr(4);
            case TYPE_PAY_FORM:
                return CODE_PAY_FORM + "-" + dateStr() + getRandomStr(4);
            case TYPE_REFUND_FORM:
                return CODE_REFUND_FORM + "-" + dateStr() + getRandomStr(4);
            case TYPE_APPROVE:
                return CODE_APPROVE + "-" + dateStr() + getRandomStr(4);
            default:
                return "";
        }
    }
}
