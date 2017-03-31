package com.cqut.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EntityIdCreateAuto {

	public static String entityIdCreate()
	{
		String entityId = "";
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss"); 
		java.util.Date  dd = Calendar.getInstance().getTime(); 

		entityId = sdf.format(dd);
//		Calendar cal = Calendar.getInstance();
//		entityId = String.valueOf(cal.get(Calendar.YEAR)) + String.valueOf(cal.get(Calendar.MONTH) + 1) + String.valueOf(cal.get(Calendar.DATE)) 
//					+ String.valueOf(cal.get(Calendar.HOUR)) + String.valueOf(cal.get(Calendar.MINUTE)) + String.valueOf(cal.get(Calendar.SECOND));
		entityId = entityId + String.valueOf((int)(Math.random()*10)) + String.valueOf((int)(Math.random()*10)) + String.valueOf((int)(Math.random()*10)) + String.valueOf((int)(Math.random()*10)); 
		return entityId;
	}
}
