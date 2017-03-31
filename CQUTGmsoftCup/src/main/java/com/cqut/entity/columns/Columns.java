package com.cqut.entity.columns;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 栏目表
 * 
 * @author lile
 * 
 */
@Entity
@DataTransferObject
public class Columns extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"columnID",
			"columnName",
			"parent",
			"icon",
			"showMenuPage",
			"columnURL",
			"showOrder"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			Integer.class,
			String.class,
			Integer.class
	};

	public Columns(){
		
	}
	
	public Columns(Map<String, Object> data){
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
	public String getColumnID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setColumnID(String columnID) {
		getProperties().put(PROPERTICE_NAME[0], columnID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getColumnID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setColumnID(key);
	}
	
	@Column
	public String getColumnName() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setColumnName(String columnName) {
		getProperties().put(PROPERTICE_NAME[1], columnName);
	}
	
	/**
	 * 父亲
	 */
	@Column
	public String getParent() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setParent(String parent) {
		getProperties().put(PROPERTICE_NAME[2], parent);
	}
	
	/**
	 * 图标
	 */
	@Column
	public Integer getIcon() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? (Integer)obj : 0;
	}

	public void setIcon(Integer icon) {
		getProperties().put(PROPERTICE_NAME[3], icon);
	}
	
	@Column
	public Boolean getShowMenuPage() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? (Boolean)obj : false;
	}

	public void setShowMenuPage(Boolean showMenuPage) {
		getProperties().put(PROPERTICE_NAME[4], showMenuPage);
	}
	
	/**
	 * 栏目链接："ColumnURL"
	 */
	@Column
	public String getColumnURL() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
	    return obj != null ? obj.toString() : null;
	}

	public void setColumnURL(String columnURL) {
		getProperties().put(PROPERTICE_NAME[5], columnURL);
	}
	
	/**
	 * 显示顺序"ShowOrder"
	 */
	@Column
	public Integer getShowOrder() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
	    return obj != null ? (Integer)obj : null;
	}

	public void setShowOrder(Integer showOrder) {
		getProperties().put(PROPERTICE_NAME[6], showOrder);
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
