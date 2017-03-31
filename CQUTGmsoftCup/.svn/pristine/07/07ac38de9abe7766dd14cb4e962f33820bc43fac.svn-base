package com.cqut.dao.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.cqut.dao.*;
import com.cqut.util.clobUtil.ClobUtil;

public class MyCommonDao {
	public void batchUpdate(String sqlTemplate, List<Object[]> list){
		JdbcUtils.batchUpdate(sqlTemplate, list);
	}
	

	/**
	* @Title: executeQuery 
	* @Description: 
	* 	执行静态Sql 查询语句，把结果集合放在一个 List<Map<String,Object>> 里面
	* @param sql
	* @return 
	* @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> executeQuery(String sql) {
		Connection con = null;
		Statement statement = null;
		ResultSet rs = null;
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		try {
			con=JdbcUtils.getConnection();
			statement = con.createStatement();
			rs = statement.executeQuery(sql);
			ResultSetMetaData md =  rs.getMetaData();
			int columnCount = md.getColumnCount();
			while(rs.next()) {
				Map<String, Object> hs = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {	// 将Clob对象转换为
					if(!(rs.getObject(i) instanceof oracle.sql.CLOB))
						hs.put(md.getColumnName(i), rs.getObject(i));
					else{
						hs.put(md.getColumnName(i),ClobUtil.ClobToString(rs.getClob(i)));
					}
			    }
				result.add(hs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.close(rs, statement, con);
		}
		return result;
	}
}

















