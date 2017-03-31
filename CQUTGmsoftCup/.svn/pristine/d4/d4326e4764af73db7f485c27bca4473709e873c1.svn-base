package com.cqut.util;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 系统ID生成器，生成18位的唯一ID。
 * 
 * <p>
 * 使用示例：<br>
 * <code>
 * 		// ....<br>		
 * 		UIDGenerator generator = UIDGenerator.getInstance();<br>
 * 		String id = generator.generate();<br>
 * 		// ...
 * </code>
 * </p>
 * @author 刘杰
 *
 */
public final class UIDGenerator {
	// 单例模式实例
	private static UIDGenerator instance;
	
	/**
	 * 返回UIDGenerator实例。
	 * @return
	 */
	public static UIDGenerator getInstance() {
		if (instance == null) {
			synchronized(UIDGenerator.class) {
				if (instance == null)
					instance = new UIDGenerator();
			}
		}
		return instance;
	}
	
	// 间隔符
	private String separator;
	// IP地址
	private final int IP;
	// 计数器
	private short counter;
	
	private UIDGenerator(){
		int ipadd;
		try {
			ipadd = toInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
		separator = "-";
		counter = (short) 0;
	}

	/**
	 * 将byte数组数据转化成int数据。
	 * @param bytes
	 * @return
	 */
	protected int toInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}

	/**
	 * 将int数据格式化为字符串
	 * @param intval
	 * @return
	 */
	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	/**
	 * 将short数据格式化为字符串
	 * @param shortval
	 * @return
	 */
	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	/**
	 * 获取系统当前毫秒数的高位(8位以上)的值。
	 * @return
	 */
	protected int getJVM() {
		return ((int) (System.currentTimeMillis() >>> 8));
	}

	/**
	 * 获取计数器值。
	 * @return
	 */
	protected synchronized short getCount() {
		if (counter < 0) {
			counter = 0;
		}
		return counter++;
	}

	protected int getIP() {
		return IP;
	}

	/**
	 * 获取系统当前毫秒数的高位(32位以上)的值。
	 * @return
	 */
	protected short getHiTime() {
		return (short) (System.currentTimeMillis() >>> 32);
	}

	/**
	 * 获取系统当前毫秒数。
	 * @return
	 */
	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}
	
	/**
	 * 将整数转化成二进制字符串。
	 * @param s
	 * @return
	 */
	protected String toBinaryBit(long s) {
		StringBuffer sb = new StringBuffer();
		while(s!=0) {
			sb.append(s & 0x01);
			s = s >>> 1;
		}
		return sb.toString();
	}

	/**
	 * 生成ID。
	 * @return
	 */
	public String generate() {
		return new StringBuffer(36)
//				.append(format(getIP())).append(separator)
//				.append(format(getJVM())).append(separator)
				.append(format(getHiTime())).append(separator)
				.append(format(getLoTime())).append(separator)
				.append(format(getCount())).toString();
	}

	public String getFileID(){
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}
	public static void main(String[] test) {
		UIDGenerator t = UIDGenerator.getInstance();
		for (int i=0; i<10000000; i++)
		{
			String g = t.generate();
			//
			//
		}
//		long curt = System.currentTimeMillis();
//		//
//		//
	}
}
