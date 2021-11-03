package store.tacomall.common.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    /**
     * 计算不同时间统计天数
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static Integer differentDayMillisecond(Date date1, Date date2) {
        int day = (int) ((date2.getTime() - date1.getTime()) / (3600 * 1000 * 24));
        return day;
    }

    public static String formatDateByPattern(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    public static Date parseDateStr(String dateStr, String dateFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = null;
        if (dateStr != null) {
            date = sdf.parse(dateStr);
        }
        return date;
    }

    // LocalDateTime转为"yyyy-MM-dd HH:mm:ss"
    public static String formatLocalDateTimeString(LocalDateTime date, String dateFormat) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        return date.format(dtf);
    }

    public static List<Date> monthToDayList(Integer year, Integer month) {
        List<Date> dayArr = new ArrayList();
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        Integer maxDate = a.get(Calendar.DATE);

        for (Integer i = 1; i <= maxDate; i++) {
            Calendar iTime = Calendar.getInstance();
            iTime.set(Calendar.YEAR, year);
            iTime.set(Calendar.MONTH, month - 1);
            iTime.set(Calendar.DATE, i);
            iTime.set(Calendar.AM_PM, 0);
            iTime.set(Calendar.HOUR, 0);
            iTime.set(Calendar.MINUTE, 0);
            iTime.set(Calendar.SECOND, 0);
            iTime.set(Calendar.MILLISECOND, 0);
            dayArr.add(iTime.getTime());
        }
        return dayArr;
    }

    public static Date monthFirstDay(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        calendar.set(Calendar.AM_PM, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date monthLastDay(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.AM_PM, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private static transient Integer gregorianCutoverYear = 1582;

    /**
     * 闰年中每月天数
     */
    private static final Integer[] DAYS_P_MONTH_LY = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    /**
     * 非闰年中每月天数
     */
    private static final Integer[] DAYS_P_MONTH_CY = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    /**
     * 代表数组里的年、月、日
     */
    private static final Integer Y = 0, M = 1, D = 2;

    /**
     * 将代表日期的字符串分割为代表年月日的整形数组
     *
     * @param date
     * @return
     */
    public static Integer[] splitYMD(String date) {
        date = date.replace("-", "");
        Integer[] ymd = { 0, 0, 0 };
        ymd[Y] = Integer.parseInt(date.substring(0, 4));
        ymd[M] = Integer.parseInt(date.substring(4, 6));
        ymd[D] = Integer.parseInt(date.substring(6, 8));
        return ymd;
    }

    /**
     * 检查传入的参数代表的年份是否为闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(Integer year) {
        return year >= gregorianCutoverYear ? ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0)))
                : (year % 4 == 0);
    }

    /**
     * 日期加1天
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    private static Integer[] addOneDay(Integer year, Integer month, Integer day) {
        if (isLeapYear(year)) {
            day++;
            if (day > DAYS_P_MONTH_LY[month - 1]) {
                month++;
                if (month > 12) {
                    year++;
                    month = 1;
                }
                day = 1;
            }
        } else {
            day++;
            if (day > DAYS_P_MONTH_CY[month - 1]) {
                month++;
                if (month > 12) {
                    year++;
                    month = 1;
                }
                day = 1;
            }
        }
        Integer[] ymd = { year, month, day };
        return ymd;
    }

    /**
     * 将不足两位的月份或日期补足为两位
     *
     * @param decimal
     * @return
     */
    public static String formatMonthDay(Integer decimal) {
        DecimalFormat df = new DecimalFormat("00");
        return df.format(decimal);
    }

    /**
     * 将不足四位的年份补足为四位
     *
     * @param decimal
     * @return
     */
    public static String formatYear(Integer decimal) {
        DecimalFormat df = new DecimalFormat("0000");
        return df.format(decimal);
    }

    /**
     * 计算两个日期之间相隔的天数
     *
     * @param begin
     * @param end
     * @return
     * @throws ParseException
     */
    public static long countDay(String begin, String end) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate, endDate;
        long day = 0;
        try {
            beginDate = format.parse(begin);
            endDate = format.parse(end);
            day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 以循环的方式计算日期
     *
     * @param beginDate endDate
     * @return
     */
    public static List<String> getEveryday(String beginDate, String endDate) {
        long days = countDay(beginDate, endDate);
        Integer[] ymd = splitYMD(beginDate);
        List<String> everyDays = new ArrayList<String>();
        everyDays.add(beginDate);
        for (Integer i = 0; i < days; i++) {
            ymd = addOneDay(ymd[Y], ymd[M], ymd[D]);
            everyDays.add(formatYear(ymd[Y]) + "-" + formatMonthDay(ymd[M]) + "-" + formatMonthDay(ymd[D]));
        }
        return everyDays;
    }

    public static void main(String[] args) {
        List<String> list = DateUtil.getEveryday("2009-02-01", "2009-03-09");
        for (String result : list) {
            System.out.println(result);
        }
    }

}
