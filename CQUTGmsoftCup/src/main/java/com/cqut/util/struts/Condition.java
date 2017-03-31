package com.cqut.util.struts;

/**
 * 条件实体
 * 
 * @author Ming
 * @description 用于封装条件参数
 * @date 2016-04-11
 */
public class Condition {
	private String sortField;
	private String order;
	private boolean needLink;
	private Integer curPage;
	private Integer limit;
	private String userName;
	private String validCode;
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public boolean isNeedLink() {
		return needLink;
	}

	public void setNeedLink(boolean needLink) {
		this.needLink = needLink;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
