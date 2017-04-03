package com.cqut.entity.codeTable;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 码表
 * 
 * @author lile
 * 
 */
@Entity
@DataTransferObject
public class CodeTable extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"codeTableCode",
			"codeType",
			"codeTableName",
			"parentCode",
			"hasChild",
			"codeTableValue",
			"level0",
			"chineseChar",
			"createId",
			"id"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			Boolean.class, 
			String.class,
			Integer.class,
			String.class,
			String.class,
			String.class
	};

	public CodeTable(){
		
	}
	
	public CodeTable(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ID
	 */
	@Id
	public String getCodeTableCode() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCodeTableCode(String codeTableCode) {
		getProperties().put(PROPERTICE_NAME[0], codeTableCode);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getCodeTableCode();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setCodeTableCode(key);
	}
	
	@Column
	public String getCodeType() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCodeType(String codeType) {
		getProperties().put(PROPERTICE_NAME[1], codeType);
	}
	
	/**
	 * 名称
	 */
	@Column
	public String getCodeTableName() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCodeTableName(String codeTableName) {
		getProperties().put(PROPERTICE_NAME[2], codeTableName);
	}
	
	/**
	 * 父亲Code
	 */
	@Column
	public String getParentCode() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setParentCode(String parentCode) {
		getProperties().put(PROPERTICE_NAME[3], parentCode);
	}
	
	/**
	 * 是否有子节点
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
	 * 码表值
	 */
	@Column
	public String getCodeTableValue() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCodeTableValue(String codeTableValue) {
		getProperties().put(PROPERTICE_NAME[5], codeTableValue);
	}
	
	/**
	 * 等级
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
	 * 拼音字母
	 */
	@Column
	public String getChineseChar() {
		Object obj = getProperties().get(PROPERTICE_NAME[7]);
		    return obj != null ? obj.toString() : null;
	}

	public void setChineseChar(String chineseChar) {
		getProperties().put(PROPERTICE_NAME[7], chineseChar);
	}
	@Column
	public String getCreateId() {
		Object obj = getProperties().get(PROPERTICE_NAME[8]);
		    return obj != null ? obj.toString() : null;
	}

	public void setCreateId(String createId) {
		getProperties().put(PROPERTICE_NAME[8], createId);
	}
	@Column
	public String getId() {
		Object obj = getProperties().get(PROPERTICE_NAME[9]);
		    return obj != null ? obj.toString() : null;
	}

	public void setId(String id) {
		getProperties().put(PROPERTICE_NAME[9], id);
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
