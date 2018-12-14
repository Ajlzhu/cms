package com.li.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author licheng
 * @description 日期时间工具类
 * @create 2018/11/12 16:22
 */

public class DateUtil {

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String HOUR_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String MONTH_PATTERN = "yyyy-MM";
    public static final String YEAR_PATTERN = "yyyy";
    public static final String MINUTE_ONLY_PATTERN = "mm";
    public static final String HOUR_ONLY_PATTERN = "HH";


    /**
     * 将日期转换为("yyyy-MM-dd HH:mm:ss")的字符串
     * @param date
     * @return java.lang.String
     */
    public static String date2Str(Date date){
        return date2Str(date, DATE_TIME_PATTERN);
    }

    /**
     * 将日期转换为指定格式的字符串
     * @param date
     * @param pattern
     * @return java.lang.String
     */
    public static String date2Str(Date date, String pattern){
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }
    /**
     * 将格式("yyyy-MM-dd HH:mm:ss")的字符串转为日期类型
     * @param str
     * @return java.util.Date
     */
    public static Date str2Date(String str){
        return str2Date(str, DATE_TIME_PATTERN);
    }
    /**
     * 将指定格式的字符串转为日期类型
     * @param str
     * @param pattern
     * @return java.util.Date
     */
    public static Date str2Date(String str,String pattern){
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}