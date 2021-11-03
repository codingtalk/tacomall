package store.tacomall.common.util;

import java.util.regex.Pattern;

public class RegUtil {
  public static Boolean isMobile(String s) {
    return Pattern.matches("^[1][3,4,5,6, 7,8,9][0-9]{9}$", s);
  }
}
