package com.cqut.service.user;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import com.cqut.dao.common.CommonDao;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.user.customInterface.IUserEntityDao;
import com.cqut.dao.user.customInterface.IUserMapDao;
import com.cqut.entity.user.User;
import com.cqut.service.crew.CrewService;
import com.cqut.service.user.customInterface.IUserService;
import com.cqut.util.StringTools;
import com.cqut.util.security.DataSecurity;
import com.cqut.util.security.KaptchaUtil;
import com.cqut.util.struts.WebUtil;
import com.cqut.util.OperatorUtil;
import com.cqut.util.struts.Permission;
import com.opensymphony.xwork2.ActionContext;

import flex.messaging.io.ArrayList;

@Controller  
@RemoteProxy
public class UserService implements IUserService {

	@Resource(name = "userMapDao")
	private IUserMapDao mapDao;
	@Resource(name = "userEntityDao")
	private IUserEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	@Resource(name = "crewService")
	private CrewService crewService;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findUsers(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findUsers(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findUsers(properties, condition, "", "", needLink, -1, -1);
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getUser(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findUsers(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public User getUserEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findUsers(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new User(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<User> findUserByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findUsers(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(User.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(User value) {
		return deleteById(value.getUserID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(User.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(User[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (User item : values) {
			ids[index++] = item.getUserID();
		}
		return deleteByIds(ids);
	}
	
	
	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new User(value));
	}


	@RemoteMethod
	public boolean saveEntity(User value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new User(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(User value) {
		User user = (User) commonDao.merge(value);
		if (user != null) {
			return user.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(User data, String condition) {
		if(mapDao.updateUser(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	
	
	@RemoteMethod
	public List<Map<String, Object>> findUserAction(String userId) {
		List<Map<String,Object>> data = new ArrayList();
		List<String> param = new ArrayList();
		param.add(userId);
		String sql = "SELECT * FROM USER WHERE userID = ? ";
		List<Object> lists = commonDao.executeAndReturnParam(sql, param);
		Object[] list = (Object[])lists.get(0);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userID", list[0]);
		map.put("QQ", list[1]);
		map.put("academy", list[2]);
		map.put("education", list[3]);
		map.put("email", list[4]);
		map.put("major", list[5]);
		map.put("name", list[6]);
		map.put("phone", list[8]);
		map.put("profession", StringTools.delNull(list[10]));
		data.add(map);
		return data;
	}
	
	//查找学院
	@RemoteMethod
	public List<Map<String, Object>> findAcademic() {
		List<Map<String,Object>> data = new ArrayList();
		String sql = "SELECT academicID, academicName FROM academic";
		List<Object> lists = commonDao.executeAndReturn(sql);
		for(int i=0;i<lists.size();i++) {
			Object[] list = (Object[])lists.get(i);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("academicID", list[0]);
			map.put("academicName", list[1]);
			data.add(map);
		}
		return data;
	}
	
	//查找专业
	@RemoteMethod
	public List<Map<String, Object>> findDiscipline(String selectAcademy) {
		List<Map<String,Object>> data = new ArrayList();
		List<String> param = new ArrayList();
		param.add(selectAcademy);
		String sql = "SELECT disciplineID, disciplineName FROM discipline WHERE academicID = ? ";
		List<Object> lists = commonDao.executeAndReturnParam(sql, param);
		for(int i=0;i<lists.size();i++) {
			Object[] list = (Object[])lists.get(i);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("disciplineID", list[0]);
			map.put("disciplineName", list[1]);
			data.add(map);
		}
		return data;
	}
	
	//查找学历
	@RemoteMethod
	public List<Map<String, Object>> findEducation() {
		List<Map<String,Object>> data = new ArrayList();
		String sql = "SELECT codeTableCode,codeTableName FROM "
				+ "codetable WHERE codeType = 'education' AND codeTableCode <> 'education'";
		List<Object> lists = commonDao.executeAndReturn(sql);
		for(int i=0;i<lists.size();i++) {
			Object[] list = (Object[])lists.get(i);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("codeTableCode", list[0]);
			map.put("codeTableName", list[1]);
			data.add(map);
		}
		return data;
	}
	
	//查找职称
	@RemoteMethod
	public List<Map<String, Object>> findProfession() {
		List<Map<String,Object>> data = new ArrayList();
		String sql = "SELECT codeTableCode, codeTableName FROM "
				+ "codetable WHERE codeType = 'profession' AND codeTableCode <> 'profession'";
		List<Object> lists = commonDao.executeAndReturn(sql);
		for(int i=0;i<lists.size();i++) {
			Object[] list = (Object[])lists.get(i);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("codeTableCode", list[0]);
			map.put("codeTableName", list[1]);
			data.add(map);
		}
		return data;
	}
	
	//检验密码是否正确
	@RemoteMethod
	public boolean isPassword(String userId,String nowPassword) {
		List<Map<String,Object>> data = new ArrayList();
		List<String> param = new ArrayList();
		param.add(userId);
		param.add(DataSecurity.stringMD5(nowPassword));
		String sql = "SELECT PASSWORD FROM USER WHERE userID = ? AND PASSWORD = ?";
		List<Object> list = commonDao.executeAndReturnParam(sql, param);
		if(list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	//修改密码
	@RemoteMethod
	public boolean updatePassword(User user,String secondPassword) {
		if(user.getPassword().equals(secondPassword)) {
			user.setPassword(DataSecurity.stringMD5(user.getPassword()));
			boolean result = this.updateEntity(user, " userID = '"+user.getUserID()+"'");
			if(result == true) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}
	
	//注册
	@RemoteMethod
	public String register(String[] properties,HttpServletRequest request) {
		int type = Integer.parseInt(properties[3]); 
		String password = DataSecurity.stringMD5(properties[2]);
		String userId = properties[0];
		int count = mapDao.findCount(new String[]{"name"},"userId = '"+userId+"'",false);
		if(count != 0){
			return "exist";
		}
		User user = new User();
		user.setUserID(userId);
		user.setName(properties[1]);
		user.setPassword(password);
		user.setType(type);
		user.setEmail(properties[4]);
		user.setQQ(properties[5]);
		user.setPhone(properties[6]);
		user.setAcademy(properties[7]);
		if(type == 0){
			user.setMajor(properties[8]);
			user.setEducation(properties[9]);
		}else{
			user.setProfession(properties[8]);
		}
		boolean isSuccess = false;
		try {
			isSuccess = saveEntity(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(isSuccess){
			try {
				request.getSession().setAttribute(OperatorUtil.OPERATOR_ID,user.getUserID());
				request.getSession().setAttribute(OperatorUtil.OPERATOR_NAME,user.getName());
				request.getSession().setAttribute(OperatorUtil.OPERATOR_TYPE,type);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "success";
	}

	@RemoteMethod
	public List<Map<String, Object>>  findUser(String userId,String type){
		String[] properties={"userId"};
		List<Map<String, Object>> data=this.findMapByPropertiesQuick(properties, "userId='"+userId+"' and type='"+type+"'", false);
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>>  findUserNew(String userId,String gameId,String teamId){
		String[] properties={"userId"};
		List<Map<String, Object>> data=crewService.findMapByPropertiesQuick(properties, 
				"userId='"+userId+"' and type!='2' and " +
						"teamId in(select distinct teamId from team where gameStepId='"+gameId+"' and gameStyle='0') " +
								"or  (teamId='"+teamId+"' and userId='"+userId+"' and type!='2')", false);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>>  findUserTeacher(String userId,String teamId){
		String[] properties={"userId"};
		List<Map<String, Object>> data=crewService.findMapByPropertiesQuick(properties, 
				"userId='"+userId+"' and type='2' and teamId='"+teamId+"'", false);
		
		return data;
	}
	
	@RemoteMethod
	public String loginValidate(String[] properties,HttpServletRequest request){
		try {
			String validCode = properties[0];
			String code = KaptchaUtil.getCode(request);
			String password = DataSecurity.stringMD5(properties[2]);
			if (!validCode.equals(code)) {
				return "validCodeError";
			}
			String condition = " UserId = '"+properties[1]+"'";
			int count = mapDao.findCount(new String[]{"name"}, condition, false);
			if(count == 0){
				return "userError";
			}
			condition = " UserId = '"+properties[1]+"' and password = '"+password+"'";
			count = mapDao.findCount(new String[]{"name"}, condition, false);
			if(count == 0){
				return "upasswordError";
			}
			List<Map<String, Object>> data = mapDao.findUsers(new String[]{"name","type"}, condition, "", "", false, -1, -1);
			Map<String, Object> map = data.get(0);
			request.getSession().setAttribute(OperatorUtil.OPERATOR_ID,properties[1]);
			request.getSession().setAttribute(OperatorUtil.OPERATOR_NAME,map.get("name"));
			request.getSession().setAttribute(OperatorUtil.OPERATOR_TYPE,map.get("type"));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	//退出登录
	@RemoteMethod
	public boolean quit(HttpServletRequest request) {
		request.getSession().invalidate();
		return true;
	}

}
