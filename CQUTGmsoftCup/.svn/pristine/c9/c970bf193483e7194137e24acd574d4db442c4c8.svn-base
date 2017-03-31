package com.cqut.service.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;

import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.role.customInterface.IRoleEntityDao;
import com.cqut.dao.role.customInterface.IRoleMapDao;
import com.cqut.entity.role.Role;
import com.cqut.service.role.customInterface.IRoleService;
import com.cqut.util.BeanUtil;

@Controller
@RemoteProxy
public class RoleService implements IRoleService {

	@Resource(name = "roleMapDao")
	private IRoleMapDao mapDao;
	@Resource(name = "roleEntityDao")
	private IRoleEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(
			String[] properties, String condition, String sortField,
			String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findRoles(properties,
				condition, sortField, order, needLink, ((curPage - 1) * limit),
				limit);

		return data;
	}

	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findRoles(properties,
				condition, sortField, order, needLink, -1, -1);

		return data;
	}

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(
			String[] properties, String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findRoles(properties,
				condition, "", "", needLink, -1, -1);

		return data;
	}

	@RemoteMethod
	public Map<String, Object> getRole(String[] properties, String condition,
			boolean needLink) {
		List<Map<String, Object>> data = mapDao.findRoles(properties,
				condition, "", "", needLink, -1, -1);

		return data != null && data.size() > 0 ? data.get(0) : null;
	}

	@RemoteMethod
	public int findCountByProperties(String[] properties, String condition,
			boolean needLink) {
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}

	public List<Role> findRoleByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findRoles(properties, condition, needLink, index,
				limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(Role.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntity(Role value) {
		return deleteById(value.getRoleID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(Role.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(Role[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (Role item : values) {
			ids[index++] = item.getRoleID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return save(new Role(value));
	}

	@RemoteMethod
	public boolean update(Role role, String condition) {
		return this.mapDao.update(role, condition) > 0;
	}

	@RemoteMethod
	public boolean save(Role value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new Role(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Role value) {
		Role role = (Role) commonDao.merge(value);
		if (role != null) {
			return role.getProperties();
		}
		return null;
	}

	/**
	 * 通过用户id找到其所对应的role列表
	 * 
	 * @param operatorCode
	 *            用户id
	 * @return
	 */
	@RemoteMethod
	public List<Object> findRolesByOperatorCode(String operatorCode) {
		String sql = "select id from RoleAssign where operatorcode='"
				+ operatorCode + "'";
		List<Object> role = this.commonDao.executeAndReturn(sql);
		return role;

	}

	@RemoteMethod
	public int addRole(String roleName) {
		// 判断此角色是否存在
		try {
			String roName = roleName.trim();
			if (roName == "" || roName.length() == 0)
				return 2;
			String sql = "select roleId from Role where roleName='" + roName
					+ "'";
			List<Object> ids = this.commonDao.executeAndReturn(sql);
			if (ids.size() > 0) {// 此角色已经存在
				return 1;
			} else {// 不存在
				Role role = new Role();
				role.setRoleID(BeanUtil.createId());
				role.setRoleName(roleName.trim());
				boolean isSuccess = save(role);
				if (isSuccess){
					return 0;
				}
				else{
					return -1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@RemoteMethod
	public boolean updateRoleName(String id, String roleName) {
		try {
			String roName = roleName.trim();
			if (roName == "" || roName.length() == 0)
				return false;
			String sql = "select roleID from Role where roleName='" + roName
					+ "'";
			List<Object> ids = this.commonDao.executeAndReturn(sql);
			if (ids.size() > 0) {// 此角色已经存在
				return false;
			} else {// 不存在
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("roleName", roleName);
				return this.mapDao.updateRoleProperties(map, "roleId='" + id + "'") > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Role getRoleEntity(String[] properties, String condition,
			boolean needLink) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveEntity(Role value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEntity(Role data, String condition) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
