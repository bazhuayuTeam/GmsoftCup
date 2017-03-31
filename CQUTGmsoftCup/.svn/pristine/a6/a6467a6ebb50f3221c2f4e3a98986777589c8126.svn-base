package com.cqut.dto.system;

import java.util.ArrayList;
import java.util.List;

public class TreeConditionItem 
{
	private String dataCode;
	private String dataLabel;
	
	private TreeConditionItem parent;
	private List<TreeConditionItem> children=new ArrayList<TreeConditionItem>();
	private Boolean childrenLoaded=false; //子节点是否加载
	
//	private Integer treeLevel;//本树中的层次
	
	private Integer state; //在树形结构中，本项是否被选择
	
	public TreeConditionItem(){
		
	}
	public TreeConditionItem(String dataCode, String dataLabel) {
		super();
		this.dataLabel = dataLabel;
		this.dataCode = dataCode;
	}
	
	public String getDataLabel() {
		return dataLabel;
	}
	public void setDataLabel(String dataLabel) {
		this.dataLabel = dataLabel;
	}
	public String getDataCode() {
		return dataCode;
	}
	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}
	public TreeConditionItem getParent() {
		return parent;
	}
	public void setParent(TreeConditionItem parent) {
		this.parent = parent;
	}
	public List<TreeConditionItem> getChildren() {
		return children;
	}
	public void setChildren(List<TreeConditionItem> children) {
		this.children = children;
	}
	public Boolean getChildrenLoaded() {
		return childrenLoaded;
	}
	public void setChildrenLoaded(Boolean childrenLoaded) {
		this.childrenLoaded = childrenLoaded;
	}
//	public Integer getTreeLevel() {
//		return treeLevel;
//	}
//	public void setTreeLevel(Integer treeLevel) {
//		this.treeLevel = treeLevel;
//	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
}
