package com.cqut.service.roleAssign;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.roleAssign.customInterface.IRoleAssignEntityDao;
import com.cqut.dao.roleAssign.customInterface.IRoleAssignMapDao;
//import com.cqut.entity.auditExpertDistribute.AuditExpertDistribute;
import com.cqut.entity.roleAssign.RoleAssign;

import com.cqut.service.roleAssign.customInterface.IRoleAssignService;

@Controller  
@RemoteProxy
public class RoleAssignService implements IRoleAssignService {

	@Resource(name = "roleAssignMapDao")
	private IRoleAssignMapDao mapDao;
	@Resource(name = "roleAssignEntityDao")
	private IRoleAssignEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findRoleAssigns(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findRoleAssigns(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findRoleAssigns(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getRoleAssign(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findRoleAssigns(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public RoleAssign getRoleAssignEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findRoleAssigns(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new RoleAssign(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<RoleAssign> findRoleAssignByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findRoleAssigns(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(RoleAssign.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(RoleAssign value) {
		return deleteById(value.getRoleAssignID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(RoleAssign.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(RoleAssign[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (RoleAssign item : values) {
			ids[index++] = item.getRoleAssignID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new RoleAssign(value));
	}

	@RemoteMethod
	public boolean saveEntity(RoleAssign value) {
		return commonDao.merge(value) != null ? true : false;
	}
	
	@RemoteMethod
	public boolean saveAllEntity(RoleAssign[] value) {
		for(int i=0;i<value.length;i++){
			if(!saveEntity(value[i])){
				return false;
			}
		}
		return true;
	}


	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new RoleAssign(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(RoleAssign value) {
		RoleAssign roleAssign = (RoleAssign) commonDao.merge(value);
		if (roleAssign != null) {
			return roleAssign.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(RoleAssign data, String condition) {
		if(mapDao.updateRoleAssign(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	
	/*
	 author:lifangxun
	 角色分配的时候判断是否已经分配了选中的这个角色给这个人员
	 */
	@RemoteMethod
	public int isExistRole(String roleID,String operatorID){
		String[] properity = {"roleAssignID"};
		List<Map<String, Object>> isExist = this.findMapByPropertiesQuick(properity, "roleID='"+roleID+"' and operatorId='"+operatorID+"'", false);
		
		if(isExist.size() != 0){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	//按条件删除
	@RemoteMethod
	public boolean deleteByCondition(String condition) {
		return commonDao.execute(condition) > 0 ? true : false;
	}
}
