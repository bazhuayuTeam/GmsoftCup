package com.cqut.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

@RemoteProxy
public class DateUtil {

	public static Date changeDateToTheDayEnd(Date date) {
		if (date != null) {
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = df1.format(date);
			dateString += " 23:59:59";
			try {
				return df2.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	@RemoteMethod
	public static int getWeekOfYear(Date date) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setFirstDayOfWeek(Calendar.MONDAY); // 设置每周的第一天为星期一
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 每周从周一开始
			cal.setMinimalDaysInFirstWeek(1); // 设置每周最少为1天
			cal.setTime(date);
			return cal.get(Calendar.WEEK_OF_YEAR);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static String dateFormat(Date date,String format){
		if(date == null){
			return "";
		}
		if(format == "" || format ==null){
			format = "yyyy-MM-dd";
		}
		SimpleDateFormat sf = null;
		try {
		 	sf = new SimpleDateFormat(format);
		 	return sf.format(date);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static Date getDateFromStr(String dateStr) {
		String[] ymd = dateStr.split("-");
		Date date = new Date();
		date.setYear(Integer.parseInt(ymd[0]) - 1900);
		date.setMonth(Integer.parseInt(ymd[1]) -1);
		date.setDate(Integer.parseInt(ymd[2]));
		return date;
	}

	public static Date getStartDate(Date examDate, String dateStr) {
		String[] hm = dateStr.split(":");
		Date date = (Date)examDate.clone();
		date.setHours(Integer.parseInt(hm[0]));
		date.setMinutes(Integer.parseInt(hm[1]));
		return date;
	}

	public static Date getEndDate(Date examDate, String dateStr) {
		String[] hm = dateStr.split(":");
		Date date = (Date)examDate.clone();
		date.setHours(Integer.parseInt(hm[0]));
		date.setMinutes(Integer.parseInt(hm[1]));
		return date;
	}
	
}
