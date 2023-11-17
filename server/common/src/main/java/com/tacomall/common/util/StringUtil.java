package com.tacomall.common.util;

import java.util.Map;
import java.util.Collection;

public class StringUtil {
    public static boolean isEmpty(Object str) {
        return str == null || str.toString().length() == 0;
    }

    public static boolean isNotBlank(String str) {
        return !StringUtil.isBlank(str);
    }

    public static boolean isBlank(String str) {
        Integer strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (Integer i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }

        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (Object anObject : object) {
                if (!isNullOrEmpty(anObject)) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }
}
