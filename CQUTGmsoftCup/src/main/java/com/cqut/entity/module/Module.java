package com.cqut.entity.module;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 模块
 * 
 * @author lile
 * 
 */
@Entity
@DataTransferObject
public class Module extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"modulecode",
			"modulename",
			"url",
			"parent",
			"hasChild",
			"isEndOfModuleLevel",
			"level0",
			"moduleType",
			"isOpened",
			"moduleContent",
			"showOrder",
			"icon"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			Boolean.class, 
			Boolean.class, 
			Integer.class, 
			String.class, 
			Boolean.class, 
			String.class,
			Integer.class,
			String.class
	};

	public Module(){
		
	}
	
	public Module(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 模块代号
	 */
	@Id
	public String getModulecode() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setModulecode(String moduleCode) {
		getProperties().put(PROPERTICE_NAME[0], moduleCode);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getModulecode();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setModulecode(key);
	}
	
	@Column
	public String getModulename() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setModulename(String moduleName) {
		getProperties().put(PROPERTICE_NAME[1], moduleName);
	}
	
	/**
	 * 模块URL
	 */
	@Column
	public String getUrl() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setUrl(String url) {
		getProperties().put(PROPERTICE_NAME[2], url);
	}
	
	/**
	 * 父模块
	 */
	@Column
	public String getParent() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setParent(String parent) {
		getProperties().put(PROPERTICE_NAME[3], parent);
	}
	
	/**
	 * 是否有子
	 */
	@Column
	public Boolean getHasChild() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? (Boolean)obj : false;
	}

	public void setHasChild(Boolean hasChild) {
		getProperties().put(PROPERTICE_NAME[4], hasChild);
	}
	
	/**
	 * 是否是级末
	 */
	@Column
	public Boolean getIsEndOfModuleLevel() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? (Boolean)obj : false;
	}

	public void setIsEndOfModuleLevel(Boolean isEndOfModuleLevel) {
		getProperties().put(PROPERTICE_NAME[5], isEndOfModuleLevel);
	}
	
	/**
	 * 模块等级
	 */
	@Column
	public Integer getLevel0() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? (Integer)obj : 0;
	}

	public void setLevel0(Integer level0) {
		getProperties().put(PROPERTICE_NAME[6], level0);
	}
	
	/**
	 * 模块类型
	 */
	@Column
	public String getModuleType() {
		Object obj = getProperties().get(PROPERTICE_NAME[7]);
		    return obj != null ? obj.toString() : null;
	}

	public void setModuleType(String moduleType) {
		getProperties().put(PROPERTICE_NAME[7], moduleType);
	}
	
	/**
	 * 是否展开
	 */
	@Column
	public Boolean getIsOpened() {
		Object obj = getProperties().get(PROPERTICE_NAME[8]);
		    return obj != null ? (Boolean)obj : false;
	}

	public void setIsOpened(Boolean isOpened) {
		getProperties().put(PROPERTICE_NAME[8], isOpened);
	}
	
	/**
	 * 模块简介
	 */
	@Column
	public String getModuleContent() {
		Object obj = getProperties().get(PROPERTICE_NAME[9]);
		    return obj != null ? obj.toString() : null;
	}

	public void setModuleContent(String moduleContent) {
		getProperties().put(PROPERTICE_NAME[9], moduleContent);
	}

	/**
	 * 显示顺序
	 */
	@Column
	public Integer getShowOrder() {
		Object obj = getProperties().get(PROPERTICE_NAME[10]);
		    return obj != null ? (Integer)obj : 0;
	}

	public void setShowOrder(Integer showOrder) {
		getProperties().put(PROPERTICE_NAME[10], showOrder);
	}
	/**
	 * 模块简介
	 */
	@Column
	public String getIcon() {
		Object obj = getProperties().get(PROPERTICE_NAME[11]);
		    return obj != null ? obj.toString() : null;
	}

	public void setIcon(String icon) {
		getProperties().put(PROPERTICE_NAME[11], icon);
	}
	@Transient
	@Override
	public String[] getEntityPropertiesName() {
		return PROPERTICE_NAME;
	}

	@Transient
	@Override
	public Class<?>[] getEntityPropertiesType() {
		return PROPERTICE_TYPE;
	}

}
