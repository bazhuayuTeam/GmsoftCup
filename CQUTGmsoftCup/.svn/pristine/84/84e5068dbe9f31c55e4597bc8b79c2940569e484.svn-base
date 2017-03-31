package com.cqut.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

@RemoteProxy
public class StringOrDate {
	/**
	 * 
	 * @param date 传入日期(格式：Fri Feb 24 00:00:00 CST 2012)
	 * @param style 要格式化的样式
	 * @param type 要使用的常量类型，详细见API
	 * @return 转换后的字符串
	 */
	@RemoteMethod
	public static String dateToString(Date date, String type) {
		String str = null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (type.equals("SHORT")) {
			// 返回格式：07-1-18
			format = DateFormat.getDateInstance(DateFormat.SHORT);
			str = format.format(date);
		} else if (type.equals("MEDIUM")) {
			// 返回格式：2007-1-18
			format = DateFormat.getDateInstance(DateFormat.MEDIUM);
			str = format.format(date);
		} else if (type.equals("FULL")) {
			// 返回格式：2007年1月18日 星期四
			format = DateFormat.getDateInstance(DateFormat.FULL);
			str = format.format(date);
		} 
		//
		return str;
	}
	
	/**
	 * 
	 * @param str 要格式化的字符串(格式：yyyy-MM-dd)
	 * @param style 格式化样式
	 * @return 格式化好的日期类型
	 */
	@RemoteMethod
	public static Date stringToDate(String str) { 
		DateFormat format= new SimpleDateFormat("yyyy-MM-dd");
        Date date = null; 
        try { 
            //返回格式：Fri Feb 24 00:00:00 CST 2012 
            date = format.parse(str);  
        } catch (ParseException e) { 
            e.printStackTrace(); 
        } 
        //
        return date;  
    } 
	@RemoteMethod
	public static Date stringToDateSecond(String str) { 
		DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null; 
        try { 
            //返回格式：Fri Feb 24 00:00:00 CST 2012 
            date = format.parse(str);  
        } catch (ParseException e) { 
            e.printStackTrace(); 
        } 
        //
        return date;  
    } 
	public String getCurrentTime(String separate, String type){
		Calendar c=Calendar.getInstance();
		int year=c.get(Calendar.YEAR);
		int month=c.get(Calendar.MONTH)+1; 
		int day =c.get(Calendar.DAY_OF_MONTH); 
		int hour =c.get(Calendar.HOUR_OF_DAY); 
		int minute =c.get(Calendar.MINUTE); 
		int seconds =c.get(Calendar.SECOND);
		if(type == "yyyy-MM-dd")
			return year+separate+checkTime(month)+separate+checkTime(day);
		else if (type == "yyyy-MM-dd hh:mm:ss")
			return year+separate+checkTime(month)+separate+checkTime(day)+" "+hour+":"+checkTime(minute)+":"+checkTime(seconds);
		else 
			return year+"年"+checkTime(month)+"月"+checkTime(day)+"日  "+hour+":"+checkTime(minute)+":"+checkTime(seconds);
	}
	
	//时间格式转换 
	public String checkTime(Integer time){
		if(time<10){
			return "0"+time.toString();
		}else{
			return time.toString();
		}
	};
}
