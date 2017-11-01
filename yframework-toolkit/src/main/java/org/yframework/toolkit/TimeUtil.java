package org.yframework.toolkit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Description: TimeUtil.<br>
 * Date: 2017/7/14 16:01<br>
 * Author: ysj
 */
public enum TimeUtil
{
    INSTANCE;

    private static final String _PATTERN = "yyyyMMdd";
    private static final String _PATTERN_YEAR = "yyyy";
    private static final String _PATTERN_MONTH = "MM";
    private static final String _PATTERN_DAY = "dd";
    private static final String _PATTERN_WEEK = "E";

    public String now()
    {
        return now(_PATTERN);
    }

    public String now(String pattern)
    {
        return this.get(Instant.now(), pattern);
    }

    /**
     * 格式化时间
     *
     * @param time
     * @param pattern
     * @return
     */
    public String get(Instant time, String pattern)
    {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(time, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化时间
     *
     * @param time
     * @param pattern
     * @return
     */
    public String get(long time, String pattern)
    {
        return this.get(Instant.ofEpochMilli(time), pattern);
    }

    /**
     * 格式化时间
     *
     * @param time
     * @param pattern
     * @return
     */
    public String get(Date time, String pattern)
    {
        return this.get(time.getTime(), pattern);
    }

    /**
     * 得到当前年份
     *
     * @return 格式（yyyy）
     */
    public String getYear()
    {
        return get(Instant.now(), _PATTERN_YEAR);
    }

    /**
     * 得到当前月份字符串
     *
     * @return 格式（MM）
     */
    public String getMonth()
    {
        return get(Instant.now(), _PATTERN_MONTH);
    }

    /**
     * 得到当天字符串
     *
     * @return 格式（dd）
     */
    public String getDay()
    {
        return get(Instant.now(), _PATTERN_DAY);
    }

    /**
     * 得到当前星期字符串
     *
     * @return 格式（E）星期几
     */
    public String getWeek()
    {
        return get(Instant.now(), _PATTERN_WEEK);
    }

    /**
     * 将日期字符串转化为日期对象
     * @param dateStr
     * @param format
     * @returns {String}
     */
    public Date parseDate(String dateStr, String format){
        SimpleDateFormat loc_sdf = new SimpleDateFormat(format);
        Date result = null;
        try
        {
            result = loc_sdf.parse(dateStr);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 将日期字符串转化为日历对象
     * @param dateStr
     * @param format
     * @returns {String}
     */
    public Calendar parseCalendar(String dateStr, String format){
        Date date = this.parseDate(dateStr, format);
        Calendar result = Calendar.getInstance();
        result.setTimeInMillis(date.getTime());
        return result;
    }

}
