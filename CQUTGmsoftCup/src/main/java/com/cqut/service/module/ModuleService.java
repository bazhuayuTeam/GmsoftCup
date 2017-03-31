package com.cqut.service.module;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.module.customInterface.IModuleEntityDao;
import com.cqut.dao.module.customInterface.IModuleMapDao;
import com.cqut.entity.module.Module;

import com.cqut.service.module.customInterface.IModuleService;

@Controller  
@RemoteProxy
public class ModuleService implements IModuleService {

	@Resource(name = "moduleMapDao")
	private IModuleMapDao mapDao;
	@Resource(name = "moduleEntityDao")
	private IModuleEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findModules(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findModules(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findModules(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getModule(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findModules(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public Module getModuleEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findModules(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new Module(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		if("".equals(condition)){
			condition="true=true";
		}
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<Module> findModuleByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findModules(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(Module.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(Module value) {
		return deleteById(value.getModulecode());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(Module.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(Module[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (Module item : values) {
			ids[index++] = item.getModulecode();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new Module(value));
	}

	@RemoteMethod
	public boolean saveEntity(Module value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new Module(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Module value) {
		Module module = (Module) commonDao.merge(value);
		if (module != null) {
			return module.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(Module data, String condition) {
		if(mapDao.updateModule(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	/**
	 * @param 加载首页菜单
	 * */
	@RemoteMethod
	public List<Object> loadTopMenu(String parentCode,
			HttpServletRequest request) {
		// 到session中获取用户id
		String operatorCode = "";
		String roleID = "";
		HttpSession session = request.getSession();
		operatorCode = (String) session.getAttribute("operatorId");
		roleID =(String) session.getAttribute("roleID");
		String levelCondition = "";
		if (null == operatorCode)
			return null;
		if ("noParent".equals(parentCode)) {
			levelCondition = "( parent is null or parent='')";
		} else {	
			levelCondition = " parent='" + parentCode + "' ";
		}	
		String sql="SELECT DISTINCT modulecode,level0,modulename,url,showorder,isEndOfModuleLevel FROM Module WHERE MODULECODE IN (SELECT PER.MODULEID FROM PERMISSIONASSIGN per WHERE per.ROLEID='"+roleID+"') and "+levelCondition+" " +
				" order by showorder asc ";
		List<Object> modules = commonDao.executeAndReturn(sql);
		if (modules.size() > 0) {
			return modules;
		} else {
			return null;
		}

	}
	@RemoteMethod
	public boolean saveWithEntity(Module value) {
		String parent = value.getParent();
		value.setIsEndOfModuleLevel(true);	// 默认为末级
		value.setModuleType("ModuleType0001");
		if (parent != null) {
			String sql = "update Module set haschild = 1 where modulecode = '"
					+ value.getParent() + "'";
			this.commonDao.execute(sql);
		}	

		return commonDao.merge(value) != null ? true : false;
	}
	//新增的时候寻找最大的序号
	@RemoteMethod
	public int getMaxShowOrder(String parent){
		int showOrder = 0;
		String condition="true==true";
		if(parent != null){
			condition = "parent='"+parent+"'";	
		}else{
			condition = "parent is null or parent =''";
		}
		
		try{
			String[] properties1 = {"showOrder"};
			List<Map<String, Object>> data1 = findMapByProperties(properties1,condition, "showOrder", "asc", true); 
			if(data1.size() != 0){
				String orderString = data1.get(data1.size()-1).get("showOrder")+"";
				showOrder = Integer.parseInt(orderString);
				return showOrder;
			}else{
				return 0;
			}
		} catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}

	//用于模块的上移
	@RemoteMethod
	public int moveUp(String rowId){
		String[] spProperties = new String[]{"parent"};
		List<Map<String, Object>> curModule = findMapByPropertiesQuick(spProperties,"modulecode='"+rowId+"'",true);
		String curParent = curModule.get(0).get("parent").toString();
		
		String[] mProperties = new String[]{"modulecode","showOrder"};
		List<Map<String, Object>> modules = findMapByPropertiesQuick(mProperties,"parent='"+curParent+"' order by showOrder",true);
		int index = 0;
		for(int i = 0; i < modules.size();i++) {
			if(modules.get(i).get("modulecode").toString().equals(rowId)) {
				index = i;
				break;
			}
		}
		if(index == 0) {
			return -1;
		}else {
			try {
				String sql1 = "update module set showOrder='"+modules.get(index-1).get("showOrder").toString()+"' where modulecode='"+rowId+"'";
				String sql2 = "update module set showOrder='"+modules.get(index).get("showOrder").toString()+"' where modulecode='"+modules.get(index-1).get("modulecode").toString()+"'";
				this.commonDao.execute(sql1);
				this.commonDao.execute(sql2);
				return 1;
			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}	
		}
		
		/*if(showOrder != 0){
			try{
				String priorShowOrder = (showOrder-1)+"";
				String[] mProperties = new String[]{"modulecode"};
				List<Map<String, Object>> priorModule = findMapByPropertiesQuick(mProperties,"parent='"+curParent+"' and showOrder='"+priorShowOrder+"'",true);
				String priorModulecode = priorModule.get(0).get("modulecode").toString();

				String sql1 = "update module set showOrder='"+priorShowOrder+"' where modulecode='"+rowId+"'";
				String sql2 = "update module set showOrder='"+curShowOrder+"' where modulecode='"+priorModulecode+"'";
				this.commonDao.execute(sql1);
				this.commonDao.execute(sql2);
			
				return 1;
			} catch(Exception e){
				e.printStackTrace();
				return 0;
			}	
		}else{
			return -1;
		}*/
	}
	
	//用于模块的下移
	@RemoteMethod
	public int moveDown(String rowId){
		String[] spProperties = new String[]{"showOrder","parent"};
		List<Map<String, Object>> curModule = findMapByPropertiesQuick(spProperties,"modulecode='"+rowId+"'",true);
		String curShowOrder = curModule.get(0).get("showOrder").toString();
		String curParent = curModule.get(0).get("parent").toString();
		
		String[] mProperties = new String[]{"modulecode","showOrder"};
		List<Map<String, Object>> modules = findMapByPropertiesQuick(mProperties,"parent='"+curParent+"' order by showOrder",true);
		int index = 0;
		for(int i = 0; i < modules.size();i++) {
			if(modules.get(i).get("modulecode").toString().equals(rowId)) {
				index = i;
				break;
			}
		}
		if(index == modules.size() - 1) {
			return -1;
		}else {
			try {
				String sql1 = "update module set showOrder='"+modules.get(index+1).get("showOrder").toString()+"' where modulecode='"+rowId+"'";
				String sql2 = "update module set showOrder='"+curShowOrder+"' where modulecode='"+modules.get(index+1).get("modulecode").toString()+"'";
				this.commonDao.execute(sql1);
				this.commonDao.execute(sql2);
				return 1;
			}catch(Exception e){
				e.printStackTrace();
				return 0;
			}	
		}
/*		String[] spProperties = new String[]{"showOrder","parent"};
		List<Map<String, Object>> curModule = findMapByPropertiesQuick(spProperties,"modulecode='"+rowId+"'",true);
		String curShowOrder = curModule.get(0).get("showOrder").toString();
		String curParent = curModule.get(0).get("parent").toString();
		int showOrder =  Integer.parseInt(curShowOrder);
		int maxShowOrder = findMaxShowOrder(curParent);
		
		if(showOrder != maxShowOrder){
			try{
				String nextShowOrder = (showOrder+1)+"";
				String[] mProperties = new String[]{"modulecode"};
				List<Map<String, Object>> nextModule = findMapByPropertiesQuick(mProperties,"parent='"+curParent+"' and showOrder='"+nextShowOrder+"'",true);
				String nextModulecode = nextModule.get(0).get("modulecode").toString();

				String sql1 = "update module set showOrder='"+nextShowOrder+"' where modulecode='"+rowId+"'";
				String sql2 = "update module set showOrder='"+curShowOrder+"' where modulecode='"+nextModulecode+"'";
				this.commonDao.execute(sql1);
				this.commonDao.execute(sql2);
				
				return 1;
			} catch(Exception e){
				e.printStackTrace();
				return 0;
			}	
		}else{
			return -1;
		}*/
		
	}
	
	//在模块下移的时候寻找最大的序号
	@RemoteMethod
	public int findMaxShowOrder(String curParent){
		String[] properties = new String[]{"showOrder"};
		List<Map<String, Object>> data = findMapByPropertiesQuick(properties,"parent='"+curParent+"'",true);
		int maxShowOrder = 0;
		if(data.size() != 0){
			for(int i = 0; i < data.size(); i++){
				int showOrder = Integer.parseInt(data.get(i).get("showOrder").toString());
				if(showOrder > maxShowOrder){
					maxShowOrder = showOrder;
				}
			}
		}
		return maxShowOrder;
	}
	@RemoteMethod
	public int deleteByIdAndChild(String id, String parent) {
		int count = 0;
		if (null != id) {
			try {
				String sql = "delete from Module where moduleCode like '" + id
						+ "%'";
				count = this.commonDao.execute(sql);
				if (count > 0) {
					if (null != parent) {
						sql = "select count(moduleCode) from Module where parent ='"
								+ parent + "'";
					} else {
						sql = "select count(moduleCode) from Module where parent is null";
					}
					List<Object> list = this.commonDao.executeHqlAndReturn(sql);
					if (null != list && list.size() > 0) {
						if (((Long) list.get(0) == 0)) {
							sql = "update Module set hasChild = '0', isEndOfModuleLevel='1' where moduleCode ='"+parent+"'";
							this.commonDao.execute(sql);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		}
		return count;
	}
	@RemoteMethod
	public int deleteByIdsAndChild(String[] ids, String[] parent) {
		try {
			commonDao.delete(Module.class, ids);
			int i = ids.length;
			int index = i;
			for (int j = 0; j < ids.length; j++) {
				String sql = "delete from Module where parent like '" + ids[j]
						+ "%'";
				//
				index += this.commonDao.execute(sql);
			}

			for (int j = 0; j < ids.length; j++) {
				if (parent[j] != null && !parent[j].equals("")
						&& !parent[j].equals("null")) {
					String sql2 = "select modulecode from Module where parent = '"
							+ parent[j] + "'";
					int k = this.commonDao.execute(sql2);
					if (k == 0) {
						String sql3 = "update Module set haschild = false where modulecode = '"
								+ parent[j] + "'";
						this.commonDao.execute(sql3);
					}
				}
			}

			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@RemoteMethod
	public int updateModule(Module data, String condition) {
		return mapDao.updateModule(data.getProperties(), condition);
	}
	
	/*****************************************主页面查找模块开始 by赵鑫*********************************************************************************/
	/*
	 * 思路：根据roleId去权限表获取该角色的分配模块id，
	 *一级菜单:这个模块没有父级也没有孩子或这个模块有且只有一层孩子 
	 * 二级菜单:根据父级id来获取末级模块
	*/
	@RemoteMethod
	public List<Map<String, Object>> findFirstLevelMenu(String roleId) {
		String[] properties = {"moduleCode", "moduleName","hasChild","url","showOrder", "parent"};
		String condition = " MODULECODE IN ("
				+ "SELECT pa.MODULEID FROM PERMISSIONASSIGN pa WHERE pa.roleId = '" + roleId + "' "
				+ "AND EXISTS ("
				+ "SELECT tempMdoule.MODULECODE FROM MODULE tempMdoule "
				+ "WHERE (tempMdoule.PARENT=pa.MODULEID OR ("
				+ "tempMdoule.MODULECODE=pa.MODULEID AND (PARENT IS NULL OR PARENT='')"
				+ ")) AND tempMdoule.HASCHILD=0 AND ISOPENED=0))";
		
		return this.findMapByProperties(properties, condition, "SHOWORDER", "asc", false);
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findSecondLevelMenu(String roleId, String parentCode) {
		String[] properties = {"moduleCode", "moduleName", "hasChild", "url","showOrder","parent"};
		String condition = "MODULECODE IN ("
				+ "SELECT pa.MODULEID FROM PERMISSIONASSIGN pa "
				+ "WHERE pa.roleId = '" + roleId + "' AND EXISTS ("
				+ "SELECT tempMdoule.MODULECODE FROM MODULE tempMdoule "
				+ "WHERE tempMdoule.PARENT = '" + parentCode + "' AND tempMdoule.MODULECODE = pa.MODULEID "
				+ "AND tempMdoule.HASCHILD = 0 AND ISOPENED = 0))";
		return this.findMapByProperties(properties, condition, "SHOWORDER", "asc", false);
	}
	/*****************************************主页面查找模块结束 by赵鑫*********************************************************************************/
}


























