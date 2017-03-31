package com.cqut.service.operator;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.operator.customInterface.IOperatorEntityDao;
import com.cqut.dao.operator.customInterface.IOperatorMapDao;
import com.cqut.entity.operator.Operator;
import com.cqut.service.operator.customInterface.IOperatorService;
import com.cqut.service.roleAssign.RoleAssignService;
import com.cqut.util.BeanUtil;
import com.cqut.util.OperatorUtil;
import com.cqut.util.security.DataSecurity;

@Controller  
@RemoteProxy
public class OperatorService implements IOperatorService {

	@Resource(name = "operatorMapDao")
	private IOperatorMapDao mapDao;
	@Resource(name = "operatorEntityDao")
	private IOperatorEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	@Resource(name="beanUtil")
	private BeanUtil beanUtil;
	@Resource(name="roleAssignService")
	private RoleAssignService roleAssignService;
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findOperators(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findOperators(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findOperators(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getOperator(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findOperators(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public Operator getOperatorEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findOperators(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new Operator(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<Operator> findOperatorByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findOperators(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(Operator.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(Operator value) {
		return deleteById(value.getOperatorID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(Operator.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(Operator[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (Operator item : values) {
			ids[index++] = item.getOperatorID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new Operator(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new Operator(value));
	}

	@RemoteMethod
	public boolean updateEntity(Operator data, String condition) {
		if(mapDao.updateOperator(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	
	@RemoteMethod
	public void operatorExit(HttpServletRequest request) {
		    HttpSession session=request.getSession();
			session.removeAttribute(OperatorUtil.OPERATOR_ID);
			session.removeAttribute(OperatorUtil.ZGH);
			session.removeAttribute(OperatorUtil.OPERATOR_NAME);
			session.removeAttribute(OperatorUtil.ROLE_ID);
	}

	@RemoteMethod
	public boolean saveEntity(Operator value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@Override
	public Map<String, Object> saveAndReturn(Operator value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@RemoteMethod
	public int LoginCheck(String user,String password,HttpServletRequest request){
		HttpSession session=request.getSession();
		String[] pros = {"operatorID","name","operatorKind","ZGH"};
		Operator operator = getOperatorEntity( pros,"ZGH='"+user+"' and password='"+DataSecurity.stringMD5(password)+"'", false);
		try{
		if(operator!=null){			
		//	session.setAttribute(OperatorUtil.OPERATOR_TYPE, operator.getOperatorKind());
			String[] properties={"roleID"};
			List<Map<String,Object>> maps=roleAssignService.findMapByPropertiesQuick(properties, "operatorId='"+operator.getOperatorID()+"'", false);
			if(maps.size()>0){
				session.setAttribute("roleID", maps.get(0).get("roleID").toString());
				session.setAttribute("operatorId", operator.getOperatorID());
				session.setAttribute("operatorName", operator.getName());
				session.setAttribute("ZGH", operator.getZGH());
			}
			else{
				return 0;
			}
			return 1;
		}else{			
			return 0;
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@RemoteMethod
	public int changePassWord(String operatorCode,String oldPSW, String newPSW) {
		//try {
			String[] pros = {"operatorID"};
			String newPs=DataSecurity.stringMD5(oldPSW);
			Operator operator = getOperatorEntity(pros, "operatorID='"+operatorCode+"' and password='"+newPs+"'", false);
			if (operator!=null) {
				operator.setPassword(DataSecurity.stringMD5(newPSW));
				if(updateEntity(operator, "operatorID='"+operatorCode+"'"))
					return 1;
				return -1;
			}else{
				return 0;
			}
	}
	
	
	@RemoteMethod
	public String getID(){
		String id=beanUtil.createId();
		return id;
	}
}
