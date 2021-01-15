package com.gjl.weixin.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Author: WilliamJL
 * @Date: 2019/6/3 22:35
 * @Version 1.0
 */
public class DateUtil {

    public static final String DEFAULT_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";

    public static boolean isBefore(String time1, String time2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            return date1.before(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean isValidDate(String str) {
        boolean convertSuccess=true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2019/02/29会被接受，并转换成2019/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (Exception e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }

    public static String  Cdate(String time1, String time2){
        int days = 0;
        try {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = format.parse(time1);
            Date date2 = format.parse(time2);
            days=(int)(date2.getTime()-date1.getTime())/(1000*3600*24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(Math.abs(days));
    }

    /**
     * 计算年龄  当前日期-出生年月
     * @param endDate
     * @param birthDay
     * @return
     */
    public static int caculateAge(Date endDate, Date birthDay){
        int age =0 ;
        Calendar curDate = Calendar.getInstance();
        curDate.setTime(endDate);
        int yearNow = curDate.get(Calendar.YEAR);
        int monthNow = curDate.get(Calendar.MONTH);
        int dayOfMonthNow = curDate.get(Calendar.DAY_OF_MONTH);

        Calendar birthDayDate = Calendar.getInstance();
        birthDayDate.setTime(birthDay);
        int yearBirth = curDate.get(Calendar.YEAR);
        int monthBirth = curDate.get(Calendar.MONTH);
        int dayOfMonthBirth = curDate.get(Calendar.DAY_OF_MONTH);

        age = yearNow - yearBirth;
        if(monthNow <= monthBirth){
            if(monthNow == monthBirth){
                if(dayOfMonthNow < dayOfMonthBirth){
                    age--;
                }
            }else{
                age--;
            }
        }
        return age;
    }

    /**
     * 转换指定日期为 指定格式字符串
     * @param date
     * @param format
     * @param timeZone
     * @return
     */
    public static String DateToString(Date date, String format, TimeZone timeZone){
        if(null == date || null == format){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        if(timeZone != null){
            simpleDateFormat.setTimeZone(timeZone);
        }
        return simpleDateFormat.format(date);
    }

    /**
     * 转换指定日期为 指定格式字符串 无时区
     * @param date
     * @param format
     * @return
     */
    public static String DateToString(Date date, String format){
        return DateToString(date, format, null);
    }

    /**
     * 转换指定日期为 指定默认时间格式字符串 有时区
     * @param date
     * @param timeZone
     * @return
     */
    public static String TimeToString(Date date, TimeZone timeZone){
        return DateToString(date, DEFAULT_TIME_FORMAT, timeZone);
    }

    /**
     * 转换指定字符串格式为 指定时间 有时区
     * @param str  字符格式的日期
     * @param format  指定日期格式
     * @param timeZone
     * @return
     */
    public static Date StringToDate(String str, String format, TimeZone timeZone){
        if(str == null){
            return null;
        }
        str = str.trim();
        if(format == null){
            format = DEFAULT_TIME_FORMAT;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        if(timeZone != null){
            simpleDateFormat.setTimeZone(timeZone);
        }
        try{
            return simpleDateFormat.parse(str);
        }catch(ParseException e){
            throw new RuntimeException("validation.date.parse.error", e);
        }
    }

    /**
     * 转换指定字符串格式为 指定时间 无时区
     * @param str
     * @param format
     * @return
     */
    public static Date StringToDate(String str, String format){
        return StringToDate(str, format, null);
    }


}
