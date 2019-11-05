package com.snail.framework.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

/**
 * @功能:日期工具类
 * @作者:snail
 */
@Slf4j
public class DateTool {
	
	
	 /***
     * 格式化时间
     *
     * @param fmt 默认yyyy-MM-dd
     * @param date 默认当前时间
     * @return
     */
    public static String formatDate(String fmt, Date date) {
        if (date == null) {
            date = new Date();
        }

        SimpleDateFormat format = null;
        if (fmt == null) {
            format = new SimpleDateFormat("yyyy-MM-dd");
        } else {
            format = new SimpleDateFormat(fmt);
        }
        return format.format(date);
    }
	
    /***
     * 字符串转日期，默认yyyy-MM-dd
     *
     * @param dateStr 默认当前时间
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String fmt, String dateStr) throws ParseException {
        SimpleDateFormat format = null;
        if (fmt == null) {
            format = new SimpleDateFormat("yyyy-MM-dd");
        } else {
            format = new SimpleDateFormat(fmt);
        }
        return format.parse(dateStr);
    }
    
    
	/**
	 * @param nowTime
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ParseException 
	 */
	public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) throws ParseException {
		
		Date now=parseDate(null, formatDate(null, nowTime));
		
		Date start=parseDate(null, formatDate(null, startTime));
		
		Date end=parseDate(null, formatDate(null, endTime));
		
		
		if (now.getTime() == start.getTime() || now.getTime() == end.getTime()) {
			return true;
		}

		Calendar date = Calendar.getInstance();
		date.setTime(now);

		Calendar begin = Calendar.getInstance();
		begin.setTime(start);

		Calendar endCalend = Calendar.getInstance();
		endCalend.setTime(end);

		if (date.after(begin) && date.before(endCalend)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 取得离今天n天以后的日期的零点时刻
	 *
	 * @param n
	 * @return
	 */
	public static Date getDateAfterNowInZero(int n) {
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		now.add(Calendar.DAY_OF_MONTH, n);
		return now.getTime();
	}
	
	public static void main(String[] args) throws ParseException {

		Date now = parseDate(null, "2019-09-09");

		Date start = parseDate(null, "2019-09-08");

		Date end = parseDate(null, "2019-09-11");

		if (now.getTime() == start.getTime() || now.getTime() == end.getTime()) {
			System.out.println(true);

			return;
		}

		Calendar date = Calendar.getInstance();
		date.setTime(now);

		Calendar begin = Calendar.getInstance();
		begin.setTime(start);

		Calendar endCalend = Calendar.getInstance();
		endCalend.setTime(end);

		if (date.after(begin) && date.before(endCalend)) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}

	}


}
