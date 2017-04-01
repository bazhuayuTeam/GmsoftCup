package com.cqut.service.notice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.dao.common.ICommonDao;
import com.cqut.dao.notice.customInterface.INoticeEntityDao;
import com.cqut.dao.notice.customInterface.INoticeMapDao;
import com.cqut.entity.notice.Notice;

import com.cqut.service.notice.customInterface.INoticeService;

@Controller  
@RemoteProxy
public class NoticeService implements INoticeService {

	@Resource(name = "noticeMapDao")
	private INoticeMapDao mapDao;
	@Resource(name = "noticeEntityDao")
	private INoticeEntityDao entityDao;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit) {
		List<Map<String, Object>> data = mapDao.findNotices(properties, condition, sortField, order, needLink, ((curPage-1)*limit), limit);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findNotices(properties, condition, sortField, order, needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink) {
		List<Map<String, Object>> data = mapDao.findNotices(properties, condition, "", "", needLink, -1, -1);
		
		return data;
	}
	
	@RemoteMethod
	public Map<String, Object> getNotice(String[] properties,
			String condition, boolean needLink){
		List<Map<String, Object>> data = mapDao.findNotices(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? data.get(0) : null;
	}
	
	public Notice getNoticeEntity(String[] properties,
			String condition, boolean needLink){
			List<Map<String, Object>> data = mapDao.findNotices(properties, condition, "", "", needLink, -1, -1);
		
		return data != null && data.size() > 0 ? new Notice(data.get(0)) : null;
	}
	
	@RemoteMethod
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink){
		int length = mapDao.findCount(properties, condition, needLink);
		return length;
	}
	
	public List<Notice> findNoticeByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit) {
		return entityDao.findNotices(properties, condition, needLink, index, limit);
	}

	@RemoteMethod
	public boolean deleteById(String id) {
		try {
			commonDao.delete(Notice.class, id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RemoteMethod
	public boolean deleteByEntity(Notice value) {
		return deleteById(value.getNoticeId());
	}

	@RemoteMethod
	public boolean deleteByIds(String[] ids) {
		try {
			commonDao.delete(Notice.class, ids);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@RemoteMethod
	public boolean deleteByEntitys(Notice[] values) {
		String[] ids = new String[values.length];
		int index = 0;
		for (Notice item : values) {
			ids[index++] = item.getNoticeId();
		}
		return deleteByIds(ids);
	}

	@RemoteMethod
	public boolean save(Map<String, Object> value) {
		return saveEntity(new Notice(value));
	}

	@RemoteMethod
	public boolean saveEntity(Notice value) {
		return commonDao.merge(value) != null ? true : false;
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Map<String, Object> value) {
		return saveAndReturn(new Notice(value));
	}

	@RemoteMethod
	public Map<String, Object> saveAndReturn(Notice value) {
		Notice notice = (Notice) commonDao.merge(value);
		if (notice != null) {
			return notice.getProperties();
		}
		return null;
	}
	
	@RemoteMethod
	public boolean updateEntity(Notice data, String condition) {
		if(mapDao.updateNotice(data.getProperties(), condition)==1){
			return true;
		}
		return false;
	}
	

}
