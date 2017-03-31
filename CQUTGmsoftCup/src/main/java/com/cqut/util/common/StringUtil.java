package com.cqut.util.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringUtil {

	public static String[] split(String source, String str){
		if(source == null){
			return new String[0];
		}
		List<String> temp = new ArrayList<String>();
		int index = source.indexOf(str);
		while(index != -1){
			temp.add(source.substring(0, index));
			source = source.substring(index + str.length());
			index = source.indexOf(str);
		}
		temp.add(source);
		return temp.toArray(new String[temp.size()]);
	}
	
	/**
	 * 以某字符开头
	 * @return
	 */
	public static boolean startWith(String source, String str){
		String temp = source + "";
		return temp.trim().toLowerCase().startsWith(str.toLowerCase());
	}
	
	/**
	 * 以某些字符开头
	 * @return
	 */
	public static boolean startWith(String source, String[] strs){
		for(String str : strs){
			if(startWith(source, str)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 截取最后一个出现该字符的字符串段
	 * @param source
	 * @param str
	 * @return
	 */
	public static String subStringByLastFlag(String source, String str){
		if(source == null){
			return source;
		}
		int index = source.lastIndexOf(str);
		if(index != -1){
			return source.substring(index + str.length());
		}
		return source;
	}
	
	public static boolean notEmpty(String value){
		return value != null && !value.replace("\"", "").toLowerCase().equals("null") && value.length() > 0 ? true : false;
	}
	
	public static boolean isEmpty(String value){
		return !notEmpty(value);
	}
	
	public static boolean notEmpty(Object obj){
		return obj!=null && obj.toString().length() > 0 && !obj.toString().replace("\"", "").toLowerCase().equals("null") ? true:false; 
	}
	
	public static boolean isEmpty(Object obj){
		return !notEmpty(obj); 
	}
	
	/**
	 * 将浮点型数据保留小数点后K位
	 */
	public static String decimal(String numStr,int k){
		int point=numStr.indexOf(".");
		//
		if(point==-1||point+k+1>numStr.length()){//小数点不存在或者本来就满足条件
			//
			return numStr;
		}
		return numStr.substring(0, point+k+1);
	}
	
	public static String htmlspecialchars(String str) {
		if(str==null){
			return "";
		}
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		//
		return str;
	}
	
	/**
	 * 给定一个List和指定分割符，将他倒序打印出来
	 * @param list
	 * @param spiltChar
	 * @return
	 */
	public static String spiltListByReverse(List<String> list, String spiltChar) {
		String strs = "";
		if(list != null) {
			Collections.reverse(list);
		    for(String str : list) {
		    	strs += str + spiltChar;
			}
		    int addressStrLen = strs.length();
		    if(addressStrLen > 0) {
		    	strs = strs.substring(0, strs.length()-spiltChar.length());
		    }
		}
		return strs;
	}
}
