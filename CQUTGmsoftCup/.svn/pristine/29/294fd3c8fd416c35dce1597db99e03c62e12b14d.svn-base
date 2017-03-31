package com.cqut.service.permissionAssign;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.permissionAssign.customInterface.IPermissionAssignEntityDao;
import com.cqut.dao.permissionAssign.customInterface.IPermissionAssignMapDao;
import com.cqut.entity.permissionAssign.PermissionAssign;
import com.cqut.service.module.customInterface.IModuleService;
import com.cqut.service.permissionAssign.customInterface.IPermissionAssignService;
import com.cqut.util.BeanUtil;
import com.sun.xml.internal.bind.v2.model.core.ID;

@Controller  
@RemoteProxy
public class PermissionAssignService implements IPermissionAssignService {

	@Resource(name = "permissionAssignMapDao")
	private IPermissionAssignMapDao mapDao;
	@Resource(name = "permissionAssignEntityDao")
	private IPermissionAssignEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	@Resource(name = "moduleService")
	private IModuleService moduleService;
	private static final int LEVEL_CODE_LENGTH = 4;	
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findPermissionAssigns(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findPermissionAssigns(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findPermissionAssigns(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getPermissionAssign(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findPermissionAssigns(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public PermissionAssign getPermissionAssignEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findPermissionAssigns(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new PermissionAssign(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<PermissionAssign> findPermissionAssignByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findPermissionAssigns(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(PermissionAssign.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(PermissionAssign value) {
		return deleteById(value.getPermissionAssignID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(PermissionAssign.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(PermissionAssign[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (PermissionAssign item : values) {
			ids[index++] = item.getPermissionAssignID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new PermissionAssign(value));
	}

	@RemoteMethod
	public boolean saveEntity(PermissionAssign value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new PermissionAssign(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(PermissionAssign value) {
		PermissionAssign permissionAssign = (PermissionAssign) commonDao.merge(value);
		if (permissionAssign != null) {
			return permissionAssign.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(PermissionAssign data, String condition) {
		if(mapDao.updatePermissionAssign(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}

/*	@RemoteMethod
	public boolean addPermission(String moduleId,String roleId){
		String[] pro = {"moduleCode","parent"};
		String condition = "parent='"+moduleId+"'";
		if(moduleId == null || moduleId.equals(""))
			condition = "1 = 1";
		List<Map<String, Object>> moduledata= moduleService.findMapByProperties(pro, condition, "", "", false);
		if (moduledata.size()!=0) {
			for (int i = 0; i < moduledata.size(); i++) {
				try {
					String sql="insert into PermissionAssign values('"+BeanUtil.createId()+"','"+moduledata.get(i).get("moduleCode") + "','"+roleId+"')";
					commonDao.execute(sql);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		try {
			String sql="insert into PermissionAssign values('"+BeanUtil.createId()+"','"+moduleId+"','"+roleId+"')";
			commonDao.execute(sql);
			String parent = getParentNotInModuleCodes(moduledata);
			if(parent != null){
				sql="insert into PermissionAssign values('"+BeanUtil.createId()+"','"+parent+"','"+roleId+"')";
				commonDao.execute(sql);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}*/
	
	// 将截取moduleId，获取他的父级Id以及父级id的父级等
	private List<String> getParents(String moduleId,int length){
		if(moduleId == null || moduleId.equals(""))
			return new ArrayList<String>();
		
		List<String> parents = new ArrayList<String>();
		String sub = moduleId;
		while(true){
			sub = sub.substring(0, sub.length() - length);
			if(sub.length() == 0 ){
				break;
			}
			
			parents.add(sub);
		}
		
		return parents;
	}
	
@RemoteMethod
	public boolean addPermissions(String moduleId,String roleId){
		String condition = "moduleCode LIKE '" + moduleId + "%'";
		if(moduleId == null || moduleId.equals("null"))
			condition = "1 =1 ";
		List<Map<String, Object>> moduledata= moduleService.findMapByProperties(new String[]{"moduleCode"},
				condition, "", "", false);
		List<String> parents = getParents(moduleId, LEVEL_CODE_LENGTH);
		PermissionAssign[] pas = new PermissionAssign[moduledata.size() + parents.size()];
		
		for(int i =0;i<moduledata.size();i++) {
			pas[i] = getPermissionAssign(moduledata.get(i).get("moduleCode").toString(), BeanUtil.createId(), roleId);
		}
		
		for(int i = moduledata.size(),j=0;i<pas.length;i++,j++){
			pas[i] = getPermissionAssign(parents.get(j), BeanUtil.createId(), roleId);
		}
		
		return commonDao.saveEntities(pas);
	}

	 // 获取PermissionAssign对象
	private PermissionAssign getPermissionAssign(String moduleID,String permissionAssignID,String roleId){
		PermissionAssign pa = new PermissionAssign();
		
		pa.setmoduleID(moduleID);
		pa.setPermissionAssignID(permissionAssignID);
		pa.setRoleID(roleId);
		
		return pa;
	}

	/**
	 * @param permissionCode
	 * 通过权限id 删除权限及对应的权限模块
	 * */
	@RemoteMethod
	public Boolean deletePermission(String moduleId,String roleId){
		String condition = " AND ( moduleId like'"+moduleId+"%'";

		// 判断相同父级id下是否还有同级Id
		condition += getDelParentCondition(getParents(moduleId, LEVEL_CODE_LENGTH), moduleId,roleId);
		if(moduleId == null)
			condition = " AND ( 1 = 1";
		try {
			String sql="delete from PermissionAssign where roleId='"+ roleId +"' " + condition + ")";
			commonDao.execute(sql);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// 判断相同父级id下是否还有同级Id
	private boolean existedSameLevelMoudle(String parent,String moduleId,String roleId){
		
		String sql = "SELECT pa.permissionAssignID FROM PermissionAssign pa,Module md " +
				"WHERE pa.moduleID = md.moduleCode AND pa.roleId = '" + roleId + "' AND md.parent " + (parent == null ? " IS NULL " : "= '" + parent + "'") + "  AND md.moduleCode != '" + moduleId + "'"; 
		
		return commonDao.executeAndReturn(sql).size() > 0 ? true : false;
	}
	
	// 获取要删除的父级条件
	private String getDelParentCondition(List<String> parents,String moduleId,String roleId){
		StringBuilder condition = new StringBuilder();
		
		boolean flag = false ;// 只有当最下级没有同级时，需要取消父级时，需判断该父级是否有同级，如果没有，再去该父级的父级
		for(int i=0;i<parents.size();i++){
			flag = existedSameLevelMoudle(parents.get(i), i == 0 ?moduleId : parents.get(i-1),roleId);
			if(flag)	// 当存在同级时，就不需要往下进行判断
				break;
			
			condition.append(" OR moduleId='" + parents.get(i) + "' ");
		}
		return condition.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
