package com.cqut.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.cqut.dao.common.ICommonDao;

@Component
@Controller
@RemoteProxy
public class CodeUtil {
	
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	/**
	 * @author lulin
	 * 自动生成人员Code
	 * @return 生成的operatorCode
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	@RemoteMethod
	public String createOperatorCode() throws SQLException, ClassNotFoundException {
		String result = "";
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		//查询OperatorCode最新信息
		String sql = "SELECT MAX(operatorCode) FROM Operator";
		try{
			List<Object> maxOfoperatorCodeList = commonDao.executeHqlAndReturn(sql);
			String maxOfoperatorCode = maxOfoperatorCodeList.get(0).toString();
			String beforeStr = maxOfoperatorCode.substring(0,4);
			int beforeNumber = Integer.parseInt(beforeStr);
			if(currentYear > beforeNumber) {
				result = (currentYear * 10000 + 1) + "";
			} else {
				result = (Integer.parseInt(maxOfoperatorCode) + 1) + "";
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
}
