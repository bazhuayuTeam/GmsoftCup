package com.cqut.service.util.fileManage.excel;


/**
 * 在导入导出整数类型时的格式化工具接口
 * 在进行数据导入时会调用importFormat对读取到的数据进行处理，格式化需要的类型
 * 在进行数据导出时会调用exportFormat进行处理，格式化需要的类型
 * 可以自已从IExcelDataFormater派生类，以获得自定义的导入导出效果
 * */
public interface IExcelDataFormater
{
	/**
	 * 在从Collection导出时调用该方法将传入值经过转化后返回
	 * */
	public Object exportFormat(Object obj);

	/**
	 * 在从Collection导出时调用该方法将从xls文档中读取的值经过转化后返回
	 * */
	public Object importFormat(Object obj);
}
