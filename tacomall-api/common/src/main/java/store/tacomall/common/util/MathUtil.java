package store.tacomall.common.util;

public class MathUtil {
    public static Integer getGcd(Integer a, Integer b) {
        Integer max, min;
        max = (a > b) ? a : b;
        min = (a < b) ? a : b;

        if (max % min != 0) {
            return getGcd(min, max % min);
        } else
            return min;
    }

    public static Integer getLcm(Integer n1, Integer n2) {
        return n1 * n2 / getGcd(n1, n2);
    }

    public static Integer getLcmInList(Integer[] ints) {
        Integer min = 0;
        if (ints.length == 1) {
            min = ints[0];
        } else {
            for (Integer i = 0; i < ints.length; i++) {
                if (i < ints.length - 1) {
                    min = getLcm(ints[i], ints[i + 1]);
                }
            }
        }
        return min;
    }
}
