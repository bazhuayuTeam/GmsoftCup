package com.cqut.service.codeTable;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.codeTable.customInterface.ICodeTableEntityDao;
import com.cqut.dao.codeTable.customInterface.ICodeTableMapDao;
import com.cqut.entity.codeTable.CodeTable;

import com.cqut.service.codeTable.customInterface.ICodeTableService;

@Controller  
@RemoteProxy
public class CodeTableService implements ICodeTableService {

	@Resource(name = "codeTableMapDao")
	private ICodeTableMapDao mapDao;
	@Resource(name = "codeTableEntityDao")
	private ICodeTableEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findCodeTables(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findCodeTables(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findEducation(String[] property) {
		String condition = " parentCode = '"+property[0]+"'";
		List<Map<String, Object>> data = mapDao.findCodeTables(new String[]{"codeTableCode","codeTableName"}, condition, "", "", false, -1, -1);
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findCodeTables(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getCodeTable(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findCodeTables(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public CodeTable getCodeTableEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findCodeTables(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new CodeTable(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<CodeTable> findCodeTableByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findCodeTables(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(CodeTable.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(CodeTable value) {
		return deleteById(value.getCodeTableCode());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(CodeTable.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(CodeTable[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (CodeTable item : values) {
			ids[index++] = item.getCodeTableCode();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new CodeTable(value));
	}

	@RemoteMethod
	public boolean saveEntity(CodeTable value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new CodeTable(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(CodeTable value) {
		CodeTable codeTable = (CodeTable) commonDao.merge(value);
		if (codeTable != null) {
			return codeTable.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(CodeTable data, String condition) {
		if(mapDao.updateCodeTable(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	@RemoteMethod
	public boolean saveWithEntity(CodeTable value) {
		String parent = value.getParentCode();
		if(parent!=null) {
			String sql = "update CodeTable set haschild = 1 where CodeTablecode = '"+parent+"'";
			this.commonDao.execute(sql);
		}
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public int deleteByIdAndChild(String id,String parent) {
		try {
			commonDao.delete(CodeTable.class, id);
			String sql = "delete from CodeTable where parentCode like '"+id+"%'";
			//
			int i = this.commonDao.execute(sql)+1;
			if(parent!=null&&!parent.equals("")&&!parent.equals("null")) {
				int j = this.findCountByProperties(new String[]{"codeTableCode"}, "parentCode='"+parent+"'", false);
				if(j == 0) {
					String sql3 = "update CodeTable set haschild = 0 where codeTablecode = '"+parent+"'";
					this.commonDao.execute(sql3);
				}
			}
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@RemoteMethod
	public int deleteByIdsAndChild(String[] ids,String[] parent) {
		try {
			commonDao.delete(CodeTable.class, ids);
			int len = ids.length;
			int index = 1;
			for(int j = 0;j < len; j++) {
				String sql = "delete from CodeTable where parentCode like '%"+ids[j]+"%'";
				index += this.commonDao.execute(sql);
			}
			for(int j = 0;j < len; j++) {
				if(parent[j]!=null&&!parent[j].equals("")&&!parent[j].equals("null")) {
					int k = this.findCountByProperties(new String[]{"codeTableCode"}, "parentCode='"+parent[j]+"'", false);
					if(k == 0) {
						String sql3 = "update CodeTable set haschild = 0 where CodeTablecode = '"+parent[j]+"'";
						this.commonDao.execute(sql3);
						index++;
					}
				}
				
			}
			return index;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
