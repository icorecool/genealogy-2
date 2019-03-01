package com.zhang.genealogy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期时间工具类
 *
 * @author leon zhou
 * @date 2017年5月8日
 */
public class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 默认的时间格式化类型
     */
    public final static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 简单的时间格式化类型
     */
    public final static String SIMPLE_DATE_PATTERN = "yyyyMMdd";

    /**
     * 简单的时间格式化类型
     */
    public final static String SIMPLE_DATE_PATTERN_DETAIL = "yyyyMMddHHmmss";

    /**
     * 简单的时间格式化类型
     */
    public final static String SIMPLE_DATE_PATTERN_DETAIL_SSS = "yyyyMMddHHmmssSSS";

    /**
     * 时间格式化类型
     */
    protected final static String DATE_PATTERN_DETAIL = "yyyy-MM-dd HH mm ss";

    /**
     * 默认的时间格式化类型
     */
    public final static String DEFAULT_DATE_PATTERN_DETAIL = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认的时间格式化类型
     */
    public final static String DEFAULT_DATE_PATTERN_DETAIL_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 默认分钟的时间格式化类型
     */
    public final static String DEFAULT_DATE_PATTERN_MINUTE = "yyyy-MM-dd HH:mm";

    /**
     * 默认小时的时间格式化类型
     */
    public final static String DEFAULT_DATE_PATTERN_HOUR = "yyyy-MM-dd HH";

    /**
     * 通用的时间格式化类型
     */
    public final static String COMMON_DATE_PATTERN = "yyyy/MM/dd";

    /**
     * 默认年月格式化类型
     */
    public final static String DEFAULT_DATE_PATTERN_YEAR_MONTH = "yyyy-MM";

    /**
     * 工具类不需要实例化
     */
    private DateUtil() {
    }

    ;

    /**
     * 字符类型装成指定格式的时间格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date str2Date(String date, String pattern) {
        Date _d = null;
        if (CommonUtil.isEmpty(date)) {
            return null;
        }
        try {
            _d = new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            logger.error("解析时间出错：", e);
        }
        return _d;
    }

    /**
     * 字符类型装成指定格式的时间格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date strToDate(String date, String pattern) {
        Date _d = null;
        if (CommonUtil.isEmpty(date)) {
            return null;
        }
        try {
            _d = new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            logger.error("解析时间出错：", e);
        }
        return _d;
    }

    /**
     * 字符类型装成默认的时间格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date str2Date(String date) {
        return str2Date(date, DEFAULT_DATE_PATTERN);
    }

    /**
     * 字符类型装成默认的时间格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date strToDate(String date) {
        return strToDate(date, DEFAULT_DATE_PATTERN);
    }

    /**
     * 字符类型转成timestamp的时间格式
     *
     * @param date
     * @return
     */
    public static Date str2DateDetail(String date) {
        return str2Date(date, DATE_PATTERN_DETAIL);
    }

    /**
     * 时间转成字符串
     *
     * @param date
     * @return
     */
    public static String date2Str(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat newstr = new SimpleDateFormat(pattern);
        return newstr.format(date);
    }

    /**
     * 时间转换成字符串-默认格式"yyyy-MM-dd"
     *
     * @param date
     * @return
     */
    public static String date2Str(Date date) {
        return date2Str(date, DEFAULT_DATE_PATTERN);
    }

    /**
     * 某个时间点添加几天后的时间
     *
     * @param date 某个时间
     * @param days 需要添加的时间
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 给日期加上一天
     *
     * @param date
     * @return
     */
    public static Date addDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * 某个时间点添加几月后的时间
     *
     * @param date   某个时间
     * @param months 需要添加月数
     * @return
     */
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 增加年份
     *
     * @param date
     * @param months
     * @return
     */
    public static Date addYears(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    /**
     * 取得当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 取默认格式的当前日期字符串"yyyy-MM-dd"
     *
     * @return
     */
    public static String getCurrentDateStr() {
        SimpleDateFormat newstr = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return newstr.format(getCurrentDate());
    }

    /**
     * 取简单格式的当前日期字符串"yyyyMMdd"
     *
     * @return
     */
    public static String getSimpleCurrentDateStr() {
        SimpleDateFormat newstr = new SimpleDateFormat(SIMPLE_DATE_PATTERN);
        return newstr.format(getCurrentDate());
    }

    /**
     * 取当前日期字符串"yyyy-MM-dd"
     *
     * @return
     */
    public static String getCurrentDateStr(String pattern) {
        SimpleDateFormat newstr = new SimpleDateFormat(pattern);
        return newstr.format(getCurrentDate());
    }

    /**
     * 获取当前时间往前一个月的开始日期
     *
     * @return
     */
    public static String getOneMonthStartDate() {
        SimpleDateFormat newstr = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1); // 得到前一个月
        return newstr.format(calendar.getTime()).toString();
    }

    /**
     * 获取当前时间往前一个月的结束日期
     *
     * @return
     */
    public static String getOneMonthEndDate() {
        SimpleDateFormat newstr = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return newstr.format(new Date()).toString();
    }

    /**
     * 比较两个时间大小，date1<=date2返回true，反之返回false
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDate(Date date1, Date date2) {
        long day1 = date1.getTime();
        long day2 = date2.getTime();
        if (day1 <= day2) {
            return true;
        }
        return false;
    }

    /**
     * 获取日期详细信息
     *
     * @param date
     * @return
     */
    public static Map<String, Integer> getDateFields(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        Map<String, Integer> map = new HashMap<>();
        map.put("year", year);
        map.put("month", month);
        map.put("day", day);
        return map;
    }

    /**
     * 将指定时间重置为当天凌晨时间
     *
     * @param date
     * @return
     */
    public static Date moveBeginOfDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取当前时间一年的日期
     *
     * @return
     */
    public static Date getOneYearStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 0); // 得到前一个月
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 1);
        return calendar.getTime();
    }

    /**
     * 获取当月初时间
     *
     * @return
     */
    public static Date getCurrentMonthBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取指定年份开始时间
     *
     * @return
     */
    public static Date getYearBegin(String yearStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Calendar calendar = GregorianCalendar.getInstance();
        try {
            calendar.setTime(sdf.parse(yearStr.toString()));
        } catch (ParseException e) {
            logger.error("解析时间出错：", e);
        }
        return calendar.getTime();
    }

    /**
     * 通过日期获取年份
     *
     * @param date
     * @return
     */
    public static int getYearFromDate(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        return cld.get(Calendar.YEAR);
    }

    /**
     * 根据年月获取初始日期
     *
     * @param yearMonth
     * @return
     */
    public static Date getDateFromYearMonth(String yearMonth) {
        DateFormat ym = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = ym.parse(yearMonth);
        } catch (ParseException e) {
            logger.error("解析时间出错：", e);
        }
        return date;
    }

    /**
     * 根据年月日获取日期
     *
     * @param yearMonth
     * @return
     */
    public static Date getDateFromYearMonthDay(String yearMonth) {
        DateFormat ym = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = ym.parse(yearMonth);
        } catch (ParseException e) {
            logger.error("解析时间出错：", e);
        }
        return date;
    }

    /**
     * 获取这个月的年份
     *
     * @return
     */
    public static int getThisMonthOfYear() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 获取毫秒的数据
     *
     * @return
     */
    public static String getMillisecondStr() {
        String result = date2Str(getCurrentDate(), "yyMMddHHmmssSSS");
        return result;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     */
    public static int daysBetween(Date smdate, Date bdate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        if ((time2 - time1) % (1000 * 3600 * 24) > 0) {
            between_days = between_days + 1;
        }
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取当前星期时间
     *
     * @return
     */
    public static int weekDay() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return day;
    }

    /**
     * 获取当前小时时间（24小时制）
     *
     * @return
     */
    public static int currentHour() {
        Calendar cal = Calendar.getInstance();
        int time = cal.get(Calendar.HOUR_OF_DAY);
        return time;
    }

    /**
     * 给指定的时间加上相应的天数
     *
     * @param dateTime
     * @param number
     * @return
     */
    public static String getDayIncrement(Date dateTime, int number) {
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(dateTime);
        dateCalendar.add(Calendar.DAY_OF_MONTH, number);

        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return sdf.format(dateCalendar.getTime());
    }

    /**
     * 获取当天的开始时间
     *
     * @return
     */
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    /**
     * 给Date加上23:59:59
     *
     * @param time
     * @return
     */
    public static Date convertHourMS(Date time) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(time);
        rightNow.add(Calendar.HOUR, 23);
        rightNow.add(Calendar.MINUTE, 59);
        rightNow.add(Calendar.SECOND, 59);
        time = rightNow.getTime();
        return time;
    }


    /**
     * 给Date加上00:00:00
     *
     * @param time
     * @return
     */
    public static Date convertHourMSInit(Date time) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(time);
        rightNow.add(Calendar.HOUR, 00);
        rightNow.add(Calendar.MINUTE, 00);
        rightNow.add(Calendar.SECOND, 00);
        time = rightNow.getTime();
        return time;
    }

    /**
     * 获取当天的结束时间
     *
     * @return
     */
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取昨天的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfYesterday() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayBegin());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取昨天的结束时间
     *
     * @return
     */
    public static Date getEndDayOfYesterDay() {
        Calendar cal = new GregorianCalendar();
        cal.setTime(getDayEnd());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获取本月的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取本月的结束时间
     *
     * @return
     */
    public static Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }

    /**
     * 获取今年是哪一年
     *
     * @return
     */
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    /**
     * 获取本月是哪一月
     *
     * @return
     */
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    /**
     * 获取某个日期的开始时间
     *
     * @param d
     * @return
     */
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取某个日期的结束时间
     *
     * @param d
     * @return
     */
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }

        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取某个日期的结束时间
     *
     * @param d
     * @return
     */
    public static Timestamp getDayBeginTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }

        calendar.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 00, 00, 00);
        calendar.set(Calendar.MILLISECOND, 000);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取上个月的第一天
     *
     * @return
     */
    public static Timestamp getBeforeFirstMonthdate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取上个月的最后一天
     *
     * @return
     */
    public static Timestamp getBeforeLastMonthdate() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return getDayEndTime(calendar.getTime());
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static Timestamp getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取本周的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfWeek() {

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    /**
     * 获取本周的结束时间
     *
     * @return
     */
    public static Date getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    /**
     * 获取上周的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfLastWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        Date weekBeginSta = calendar.getTime();
        return getDayStartTime(weekBeginSta);
    }

    /**
     * 获取上周的结束时间
     *
     * @return
     */
    public static Date getEndDayOfLastWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = calendar.getTime();
        return getDayEndTime(weekEndSta);
    }

    /**
     * 获取上月的开始时间
     *
     * @return
     */
    public static Date getBeginDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取上月的结束时间
     *
     * @return
     */
    public static Date getEndDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 2, day);
        return getDayEndTime(calendar.getTime());
    }

    /**
     * 返回某个日期前几天的日期
     *
     * @param date
     * @param i
     * @return
     */
    public static Date getFrontDay(Date date, int i) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
        return cal.getTime();
    }

    /**
     * 返回默认的过期时间
     *
     * @return
     */
    public static Date getDefaultExpireTime() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, 2099);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 返回默认的生效时间
     *
     * @return
     */
    public static Date getDefaultValidTime() {
        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得距指定时间多少年/天/小时/分钟等前的日期
     *
     * @param date     时间
     * @param timeType 时间类型
     * @param date     时间数量
     * @param format   格式
     * @return
     */
    public static Date getBeforeTime(Date date, int timeType, int timeCount) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(timeType, now.get(timeType) - timeCount);
        return now.getTime();
    }


    /**
     * 获取指定时间的指定部分的数值
     *
     * @param date
     * @param timeType
     * @return
     */
    public static int getPartOfTime(Date date, int timeType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(timeType);
    }


    /**
     * 将字符串转换成yyyy-MM-dd格式
     *
     * @param str
     * @return
     */
    public static Date strToDates(String str) throws ParseException {
        Date date = new Date();
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        date = format1.parse(str);
        return date;
    }

    /**
     * 日期返回
     *
     * @param day
     * @param minute
     * @param second
     * @param millisecond
     * @return
     */
    public static Date dateConvert(int day, int minute, int second, int millisecond) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, day);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, millisecond);
        return cal.getTime();
    }

    /**
     * 将时间戳(毫秒)转换成日期
     *
     * @param timeStamp
     * @return
     */
    public static Date fmtTimestamp2Date(Long timeStamp) {
        if (timeStamp == null) {
            timeStamp = 0L;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date temp = null;
        if (timeStamp != null) {
            try {
                String str = sdf.format(timeStamp);
                temp = sdf.parse(str);
            } catch (ParseException e) {
                logger.error("解析时间出错：", e);
            }
        }
        return temp;
    }

    /**
     * 获取当天某个小时的开始时间
     *
     * @param hour
     * @return
     */
    public static Date getHourOfDayBegin(int hour) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当天某个小时的开始时间
     *
     * @param hour
     * @return
     */
    public static Date getHourOfDayend(int hour) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取时间段数组，根据时间单位获取时间段开始时间和结束时间
     *
     * @param beginTiem
     * @param endTime
     * @param timeUnit
     * @param intervalTime
     * @return
     */
    public static List<Date[]> getTimebeginAndEndArr(Date beginTiem, Date endTime,
                                                     int timeUnit, int intervalTime) {
        List<Date[]> dateArrList = new ArrayList<Date[]>();
        Date nextBegintime = beginTiem;//初始化时为参数参入的开始时间

        for (; ; ) {
            Date[] dateArr = new Date[2];
            Date arrEndtime = DateUtil.getAfterTimeByTimeUnit(nextBegintime, timeUnit, intervalTime);
            dateArr[0] = nextBegintime;

            if (arrEndtime.after(endTime)) {
                dateArr[1] = endTime;
                dateArrList.add(dateArr);
                break;//超过结束时间
            } else {
                dateArr[1] = arrEndtime;
            }
            nextBegintime = arrEndtime;
            dateArrList.add(dateArr);
        }
        return dateArrList;
    }

    /**
     * 根据时间单位加上相应的时间
     *
     * @param begintime
     * @param timeUnit
     * @param timeamount
     * @return
     */
    public static Date getAfterTimeByTimeUnit(Date begintime, int timeUnit, int timeamount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(begintime);
        calendar.add(timeUnit, timeamount);
        return calendar.getTime();
    }

    /**
     * 获取时间段内的天数列表
     *
     * @param begintime
     * @param endTime
     * @return
     */
    public static List<String> splitDays(Date begintime, Date endTime) {
        int daysNum = DateUtil.daysBetween(begintime, endTime);
        List<String> dateStrList = new ArrayList<>();
        for (int i = 0; i < daysNum; i++) {
            Date date = DateUtil.addDays(begintime, i);//一天的开始时间
            dateStrList.add(DateUtil.date2Str(date, DateUtil.DEFAULT_DATE_PATTERN));
        }
        return dateStrList;
    }

}