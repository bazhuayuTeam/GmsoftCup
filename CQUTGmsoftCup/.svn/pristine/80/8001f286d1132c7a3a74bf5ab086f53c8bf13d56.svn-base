package com.cqut.util.clobUtil;

import java.io.*;
import java.sql.*;

public class ClobUtil {
	public static String ClobToString(Clob clob) {
		String clobStr = "";
		Reader is = null;
		try {
			is = clob.getCharacterStream();
			// 得到流
			BufferedReader br = new BufferedReader(is);
			String s = null;
			s = br.readLine();
			StringBuffer sb = new StringBuffer();
			// 执行循环将字符串全部取出赋值给StringBuffer，由StringBuffer转成String
			while (s != null) {
				sb.append(s);
				s = br.readLine();
			}
			clobStr = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clobStr;
	}
}
