package com.tacomall.common.util;

public class CodeUtil {

    private static final String MEMBER_INVITE_CODE_STR_FORMAT = "00000000";

    private RedisUtil redisUtil;

    public CodeUtil(RedisUtil rdu) {
        redisUtil = rdu;
    }

}
