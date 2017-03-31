package com.cqut.entity.discipline;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;
import java.util.Date;

import com.cqut.entity.AbstractEntity;

/**
 * 专业表实体
 * 
 * @author yuantingfei
 * 
 */
@Entity
@DataTransferObject
public class Discipline extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"disciplineID",
			"academicID",
			"disciplineName",
			"disciplineShort",
			"disciplineType",
			"lengthOfschool",
			"enrollTime",
			"majorCode",
			"recruitType",
			"propertyType",
			"graduate",
			"curStudent",
			"curMaster",
			"undergraduate",
			"degreeType"
	};
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class, 
			String.class,
			String.class,
			String.class,
			String.class,
			String.class,
			String.class,
			String.class,
			String.class,
			String.class
	};

	public Discipline(){
		
	}
	
	public Discipline(Map<String, Object> data){
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 专业ID
	 */
	@Id
	public String getDisciplineID() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		    return obj != null ? obj.toString() : null;
	}

	public void setDisciplineID(String disciplineID) {
		getProperties().put(PROPERTICE_NAME[0], disciplineID);
	}
	
	@Transient
	@Override
	public String getEntityKey() {
		return getDisciplineID();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setDisciplineID(key);
	}
	
	/**
	 * 部门ID
	 */
	@Column
	public String getAcademicID() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		    return obj != null ? obj.toString() : null;
	}

	public void setAcademicID(String academicID) {
		getProperties().put(PROPERTICE_NAME[1], academicID);
	}
	
	/**
	 * 专业名称
	 */
	@Column
	public String getDisciplineName() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		    return obj != null ? obj.toString() : null;
	}

	public void setDisciplineName(String disciplineName) {
		getProperties().put(PROPERTICE_NAME[2], disciplineName);
	}
	
	/**
	 * 专业简称
	 */
	@Column
	public String getDisciplineShort() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		    return obj != null ? obj.toString() : null;
	}

	public void setDisciplineShort(String disciplineShort) {
		getProperties().put(PROPERTICE_NAME[3], disciplineShort);
	}
	
	/**
	 * 专业性质
	 */
	@Column
	public String getDisciplineType() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		    return obj != null ? obj.toString() : null;
	}

	public void setDisciplineType(String disciplineType) {
		getProperties().put(PROPERTICE_NAME[4], disciplineType);
	}
	
	/**
	 * 专业年限
	 */
	@Column
	public String getLengthOfschool() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		    return obj != null ? obj.toString() : null;
	}

	public void setLengthOfschool(String lengthOfschool) {
		getProperties().put(PROPERTICE_NAME[5], lengthOfschool);
	}
	
	/**
	 * 首届招生时间
	 */
	@Column
	public String getEnrollTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[6]);
		    return obj != null ? (String)obj : null;
	}

	public void setEnrollTime(String enrollTime) {
		getProperties().put(PROPERTICE_NAME[6], enrollTime);
	}
	/**
	 * 专业代码
	 */
	@Column
	public String getMajorCode() {
		Object obj = getProperties().get(PROPERTICE_NAME[7]);
		    return obj != null ? (String)obj : null;
	}

	public void setMajorCode(String majorCode) {
		getProperties().put(PROPERTICE_NAME[7], majorCode);
	}
	/**
	 * 招生类别
	 */
	@Column
	public String getRecruitType() {
		Object obj = getProperties().get(PROPERTICE_NAME[8]);
		    return obj != null ? (String)obj : null;
	}

	public void setRecruitType(String recruitType) {
		getProperties().put(PROPERTICE_NAME[8], recruitType);
	}
	/**
	 * 专业类别
	 */
	@Column
	public String getPropertyType() {
		Object obj = getProperties().get(PROPERTICE_NAME[9]);
		    return obj != null ? (String)obj : null;
	}

	public void setPropertyType(String propertyType) {
		getProperties().put(PROPERTICE_NAME[9], propertyType);
	}
	/**
	 * 毕业人数
	 */
	@Column
	public String getGraduate() {
		Object obj = getProperties().get(PROPERTICE_NAME[10]);
		    return obj != null ? (String)obj : null;
	}

	public void setGraduate(String graduate) {
		getProperties().put(PROPERTICE_NAME[10], graduate);
	}
	/**
	 * 在校本科生
	 */
	@Column
	public String getCurStudent() {
		Object obj = getProperties().get(PROPERTICE_NAME[11]);
		    return obj != null ? (String)obj : null;
	}

	public void setCurStudent(String curStudent) {
		getProperties().put(PROPERTICE_NAME[11], curStudent);
	}
	/**
	 * 在校硕士生
	 */
	@Column
	public String getCurMaster() {
		Object obj = getProperties().get(PROPERTICE_NAME[12]);
		    return obj != null ? (String)obj : null;
	}

	public void setCurMaster(String curMaster) {
		getProperties().put(PROPERTICE_NAME[12], curMaster);
	}
	/**
	 * 是否本科
	 */
	@Column
	public String getUndergraduate() {
		Object obj = getProperties().get(PROPERTICE_NAME[13]);
		    return obj != null ? (String)obj : null;
	}

	public void setUndergraduate(String undergraduate) {
		getProperties().put(PROPERTICE_NAME[13], undergraduate);
	}
	
	/**
	 *授位类别
	 */
	@Column
	public String getDegreeType() {
		Object obj = getProperties().get(PROPERTICE_NAME[14]);
		    return obj != null ? (String)obj : null;
	}

	public void setDegreeType(String degreeType) {
		getProperties().put(PROPERTICE_NAME[14], degreeType);
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
