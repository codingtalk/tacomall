/***
 * @Author: 码上talk|RC
 * @Date: 2021-04-13 16:47:23
 * @LastEditTime: 2021-11-02 15:32:58
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/util/SnUtil.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class SnUtil {

    private static String dateStr() {
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

    public static String gen(String type) {
        switch (type) {
        case "ORDER_FORM":
            return "HYDD-" + dateStr() + getRandomStr(4);
        default:
            ExceptionUtil.throwServerException("Sn type is error");
        }
        return "";
    }
}
