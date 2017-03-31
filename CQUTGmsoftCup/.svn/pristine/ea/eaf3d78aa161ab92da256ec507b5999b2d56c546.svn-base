package com.cqut.service.targetScore.customInterface;

import java.util.List;
import java.util.Map;

import com.cqut.entity.targetScore.TargetScore;

public interface ITargetScoreService {

	/**
	 * 查询指定属性的值
	 * 
	 * @param properties 查询的属性
	 * @param condition 查询限制条件
	 * @param needLink 是否外键链接
	 * @param curPage 当前显示的页数
	 * @param limit 每页显示数量
	 * @return
	 */
	public List<Map<String, Object>> findMapByPropertiesWithLimit(String[] properties,
			String condition, String sortField, String order, boolean needLink, int curPage, int limit);
	
	public List<Map<String, Object>> findMapByProperties(String[] properties,
			String condition, String sortField, String order, boolean needLink);
	
	public List<Map<String, Object>> findMapByPropertiesQuick(String[] properties,
			String condition, boolean needLink);
			
	public int findCountByProperties(String[] properties,
			String condition, boolean needLink);

	public List<TargetScore> findTargetScoreByProperties(String[] properties,
			String condition, boolean needLink, int index, int limit);
	
	public Map<String, Object> getTargetScore(String[] properties,
			String condition, boolean needLink);

	public TargetScore getTargetScoreEntity(String[] properties,
			String condition, boolean needLink);
	
	public boolean save(Map<String, Object> value);

	public boolean saveEntity(TargetScore value);

	public Map<String, Object> saveAndReturn(Map<String, Object> value);

	public Map<String, Object> saveAndReturn(TargetScore value);

	public boolean deleteById(String id);

	public boolean deleteByEntity(TargetScore value);

	public boolean deleteByIds(String[] ids);

	public boolean deleteByEntitys(TargetScore[] values);
	
	public boolean updateEntity(TargetScore data, String condition);
}
