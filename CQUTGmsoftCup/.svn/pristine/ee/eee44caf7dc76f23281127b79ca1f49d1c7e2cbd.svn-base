package com.cqut.util;

import java.util.List;
import java.util.Map;


import com.cqut.entity.AbstractEntity;
import com.cqut.util.common.StringUtil;

public class JSONUtil {

	public static String getGridData(int page,List<Map<String, Object>> data){
//		total	总页数
//		page	当前页
//		records	查询出的记录数
//		rows	包含实际数据的数组
//		id	行id
//		cell	当前行的所有单元格
		int size = data.size();
		String json = "{page:'"+page+"',records:'"+size+"',rows:[";
		int index = 0;
		int i = 0;
		Object temp = null;
		for(Map<String, Object> item : data){
			i=0;
			json += "{";
			for(String key : item.keySet()){
				temp = item.get(key);
				json += ""+key+":'"+(StringUtil.notEmpty(temp)?temp:"")+"'";
				if(i++ != item.size() - 1){
					json += ",";
				}
			}
			json += "}";
			if(index++ != size - 1){
				json += ",";
			}
		}
        json += "]}";  
		return json;
	}
	
	public static String getTreeData(List<AbstractEntity> datas){
		String json = "[";
		Map<String, Object> item = null;
		int size = datas.size();
		int i = 0;
		int index = 0;
		Object temp = null;
		for(AbstractEntity data : datas){
			item = data.getProperties();
			i=0;
			json += "{";
			for(String key : item.keySet()){
				temp = item.get(key);
				json += ""+key+":'"+(StringUtil.notEmpty(temp)?temp:"")+"'";
				if(i++ != item.size() - 1){
					json += ",";
				}
			}
			if(index++ != size - 1){
				json += "},";
			}else{
				json += "}]";
			}
		}
		return json;
	}

}
