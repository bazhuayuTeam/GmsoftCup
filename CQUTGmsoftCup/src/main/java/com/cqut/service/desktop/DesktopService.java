package com.cqut.service.desktop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.desktop.customInterface.IDesktopEntityDao;
import com.cqut.dao.desktop.customInterface.IDesktopMapDao;
import com.cqut.entity.desktop.Desktop;

import com.cqut.service.columns.customInterface.IColumnsService;
import com.cqut.service.desktop.customInterface.IDesktopService;
import com.cqut.util.BeanUtil;

@Controller  
@RemoteProxy
public class DesktopService implements IDesktopService {

	@Resource(name = "desktopMapDao")
	private IDesktopMapDao mapDao;
	@Resource(name = "desktopEntityDao")
	private IDesktopEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	@Resource(name = "columnsService")
	private IColumnsService columnsService;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findDesktops(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findDesktops(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findDesktops(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getDesktop(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findDesktops(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public Desktop getDesktopEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findDesktops(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new Desktop(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<Desktop> findDesktopByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findDesktops(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(Desktop.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(Desktop value) {
		return deleteById(value.getDesktopID());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(Desktop.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(Desktop[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (Desktop item : values) {
			ids[index++] = item.getDesktopID();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new Desktop(value));
	}

	@RemoteMethod
	public boolean saveEntity(Desktop value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new Desktop(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Desktop value) {
		Desktop desktop = (Desktop) commonDao.merge(value);
		if (desktop != null) {
			return desktop.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(Desktop data, String condition) {
		if(mapDao.updateDesktop(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	
	/**
	 * 保存个人桌面
	 * */
	@RemoteMethod
	public boolean savePersonDeskSetting(String operatorID, String[] columnsIDs){
		if(columnsIDs.length> 0){
			String delete="delete from desktop where operatorId='"+operatorID+"'";
			
			//删除已经存在的数据
			commonDao.execute(delete);
		}
		//存入新的数据
		for(int i = 0; i < columnsIDs.length; i++){
			String insert = " INSERT INTO desktop(deskTopID,operatorId,columnId) values ('"+BeanUtil.createId()+"','"+operatorID+"','"+columnsIDs[i]+"') ";
			commonDao.execute(insert);
		}
		return true;
	}
	
	/**
	 * 获取个人桌面
	 */
	@RemoteMethod
	public List<Map<String,Object>> getPersonDeskTop(String roleID){
		List<Map<String, Object>> resultList1=null;
		try {
			List<Map<String, Object>> resultList = this.findMapByPropertiesQuick(new String[]{"col_columnId","col_columnName","col_icon","col_columnURL","col_showOrder"}, "col.showMenuPage=1 and col.columnId in(select distinct columnId from desktop where operatorId ='"+roleID+"') order by col.showOrder asc", true);
			resultList1 = resultList;
		} catch (Exception e) {
			return null;
		}
		return resultList1;
	}
	
	//找到相应角色下的桌面内容
	@RemoteMethod
	public List<Map<String, Object>> findIframeSrc(String[] properties,String condition, boolean needLink, int index, int limit) {
		List<Map<String, Object>> list = this.findMapByProperties(properties, "1=1  order by columnId", "", "", needLink);
		
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		String[] s = { "columnId", "columnURL","showOrder"};
		for(int i=0;i < list.size(); i++){
				Map<String, Object> map = columnsService.getColumns(s,"columnId = '" + list.get(i).get("columnId") + "'", false);
				data.add(map);
		}
		return data;
	}
	
	
	@RemoteMethod
	public List<Map<String, Object>> findColumnCode(String roleID){
		String[] properties = {"columnId"};
		List<Map<String, Object>> dataList = this.findMapByPropertiesQuick(properties, "operatorId = '"+roleID+"'", false);
		return dataList;
	}
}
