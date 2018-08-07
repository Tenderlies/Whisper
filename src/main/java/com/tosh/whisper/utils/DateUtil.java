package com.tosh.whisper.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{
    public static long getTimeMillis(Date date)
    {
        return date.getTime();
    }
    
    public static long getCurrentTimeMillis()
    {
        return System.currentTimeMillis();
    }
    
    public static String getTimeString(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    
    public static String getCurrentTimeString()
    {
        return getCurrentTimeString("yyyy-MM-dd HH:mm:ss");
    }
    
    public static String getCurrentTimeString(String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }
    
    public static String getTimeStringWithMillis(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(date);
    }
    
    @SuppressWarnings("deprecation")
    public static int getWeek()
    {
        return new Date().getDay();
    }
    
    @SuppressWarnings("deprecation")
    public static String getWeekString()
    {
        return "星期" + "日一二三四五六".charAt(new Date().getDay());
    }
    
    public static Date parseToDate(String dateString)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try
        {
            date = sdf.parse(dateString);
        }
        catch (ParseException e)
        {
            System.err.println("Time parse exception!");
        }
        return date;
    }
    
    public static Date parseToDate(long timeMillis)
    {
        return new Date(timeMillis);
    }
}
