package com.cqut.util;

import java.lang.reflect.Method;

public class ConditionJudgment {
	/**
	 * 判断对象中的值是否为空
	 * @param classes 传入路径，如：com.cqut.entity.education.ExamArrange
	 * @param obj 使用的对象
	 * @return boolean
	 */
	public static boolean notEntityEmpty(String classes, Object obj) {
		boolean flag = false;
		try {
			Class<?> objClass = Class.forName(classes);// 加载类
			Method[] methods = objClass.getDeclaredMethods();// 获得类的方法集合
			// 遍历方法集合
			for (int i = 0; i < methods.length; i++) {
				// 获取所有getXX()的返回值
				// methods[i].getName()方法返回方法名
				if (methods[i].getName().startsWith("get")) {
					Object object = methods[i].invoke(obj, null);
					if(object != null){
						flag = true;
					} else {
						flag = false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
