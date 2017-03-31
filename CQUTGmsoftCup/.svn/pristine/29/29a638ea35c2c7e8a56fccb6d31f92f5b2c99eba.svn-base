package com.cqut.pages;

import java.util.HashMap;
import java.util.Map;

public abstract class AbsPage {
	private Map<String, Object> dataMap;
	private boolean refresh = true;// 刷新开关

	public AbsPage() {
		if (dataMap == null) {
			dataMap = new HashMap<String, Object>();
			//
			initData();
			//
		}
	}

	/**
	 * @param key
	 *            ：key
	 * @param data
	 *            ：数据
	 * */
	public void addData(String key, Object data) {
		dataMap.put(key, data);
	}

	/**
	 * 清除数据
	 * */
	public void clearData() {
		if(dataMap != null){
			dataMap.clear();
		}
	}

	/**
	 * 重新加载数据
	 * */
	public void reloadData() {
		if (refresh) {
			this.clearData();
			this.initData();
			this.setRefresh(false);
		}
	}
	
	/**
	 * 获得数据
	 * */
	public Object getData(String key){
		return dataMap.get(key);
	}

	/**
	 * 初始化数据
	 * */
	public abstract void initData();

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
	}

	public boolean isRefresh() {
		return refresh;
	}
}
