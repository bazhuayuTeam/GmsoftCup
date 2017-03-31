package com.cqut.service.expert;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import com.cqut.service.operator.OperatorService;
import com.cqut.service.roleAssign.RoleAssignService;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.expert.customInterface.IExpertEntityDao;
import com.cqut.dao.expert.customInterface.IExpertMapDao;
import com.cqut.entity.expert.Expert;
import com.cqut.entity.operator.Operator;
import com.cqut.entity.roleAssign.RoleAssign;

import com.cqut.service.expert.customInterface.IExpertService;
import com.cqut.util.security.DataSecurity;

@Controller  
@RemoteProxy
public class ExpertService implements IExpertService {

	@Resource(name = "expertMapDao")
	private IExpertMapDao mapDao;
	@Resource(name = "expertEntityDao")
	private IExpertEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	@Resource(name = "operatorService")
	private OperatorService operatorService;
	@Resource(name = "roleAssignService")
	private RoleAssignService roleAssignService;
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findExperts(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findExperts(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findExperts(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getExpert(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findExperts(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public Expert getExpertEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findExperts(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new Expert(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<Expert> findExpertByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findExperts(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(Expert.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(Expert value) {
		return deleteById(value.getExpertID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(Expert.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(Expert[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (Expert item : values) {
			ids[index++] = item.getExpertID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new Expert(value));
	}

	@RemoteMethod
	public boolean saveEntity(Expert value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new Expert(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Expert value) {
		Expert expert = (Expert) commonDao.merge(value);
		if (expert != null) {
			return expert.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(Expert data, String condition) {
		if(mapDao.updateExpert(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	
	@RemoteMethod
	public String getRandomNumber(){
		String[] pro = {"ZGH"};
		int s = ((int)(Math.random()*10)) + 1;
		if(s == 10){	// 其意义应该是当S=10时，将其作为9，防止位数变多
			s=9;
		}
		String num = s + "";
		for(int j=0;j<7;j++){
			num += (int)(Math.random()*10);
		}
		
		List<Map<String, Object>> data = operatorService.findMapByPropertiesQuick(pro, "ZGH = '"+num+"'", false);
		if(data.size()!=0){
			getRandomNumber();
		}
		
		return num;
	}
	
	@RemoteMethod
	public boolean saveData(Expert expert){
		//保存操作员表
		String id=operatorService.getID();
		Operator operator=new Operator();
		operator.setOperatorID(id);
		operator.setZGH(expert.getExpertID());
		operator.setPassword(DataSecurity.stringMD5(expert.getExpertID()));
		operator.setName(expert.getName());
		
		RoleAssign value=new RoleAssign();
		value.setOperatorID(id);
		value.setRoleID("20160506154705281");
		
		if(operatorService.saveEntity(operator)){
			roleAssignService.saveEntity(value);
			return this.saveEntity(expert);
		}
		return false;
	}
}
