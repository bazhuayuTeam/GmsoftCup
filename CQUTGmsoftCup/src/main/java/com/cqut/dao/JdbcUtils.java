package com.cqut.dao;

import java.sql.*;
import java.util.*;

import com.cqut.loadXML.LoadProperties;

public class JdbcUtils {

	
	private  static String drivername;
	
	private  static String url ;
	

	private static String username ;
	

	private static String password ;
	
	static{
		//	用于初始化各个参数
		init();
		
		try {
			Class.forName(drivername);
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError();
		}
	}
	
	private JdbcUtils(){
		
	}
	
	private static void init(){
	  
//		url="jdbc:mysql://localhost:3306/ed?Unicode=true&characterEncoding=UTF-8";
//		username="root";
//	    password="100677";
	//    System.out.println(JdbcUtils.class.getResource("/"));
		
	    LoadProperties load = new LoadProperties();
	    Properties pro = load.getProperties("config/jdbc.properties");
	    /*链接oracle数据的各个数据*/
		drivername = "oracle.jdbc.driver.OracleDriver";
	    url = pro.getProperty("url");
	    username = pro.getProperty("username");
	    password = pro.getProperty("password");
	}
	

	
	public  static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,username,password);
	}
	
	public static void closeConnection(Connection con) {
		if(con != null) {
			try {
				if(!con.isClosed()) {
					con.close() ;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeStatement(Statement statement) {
		if(statement != null) {
			try {
				if(!statement.isClosed()) {
					statement.close() ;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				if(!rs.isClosed()) {
					rs.close() ;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	* @Title: close 
	* @Description: 关闭数据库连接 
	* @param con
	* @param stmt
	* @param rs 
	* @return void
	 */
	public static void close(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// 批量更新
	public static void batchUpdate(String sqlTemplate, List<Object[]> list) {
		Connection conn = null;  
        PreparedStatement ps = null;  
        try {  
        	conn = getConnection();
            ps = conn.prepareStatement(sqlTemplate);  
            conn.setAutoCommit(false);  
            int size = list.size();  
            Object[] o = null;  
            for (int i = 0; i < size; i++) {  
                o = list.get(i);  
                for (int j = 0; j < o.length; j++) {  
                    ps.setObject(j + 1, o[j]);  
                }  
                ps.addBatch();  
            }  
  
            ps.executeBatch();  
            conn.commit();  
            conn.setAutoCommit(true);  
        } catch (SQLException e) {  
            e.printStackTrace();  
            try {  
                conn.rollback();  
                conn.setAutoCommit(true);  
            } catch (SQLException e1) {  
                e1.printStackTrace();  
            }  
        } finally {  
            close(null, ps, conn);  
        }  
	}
}


















