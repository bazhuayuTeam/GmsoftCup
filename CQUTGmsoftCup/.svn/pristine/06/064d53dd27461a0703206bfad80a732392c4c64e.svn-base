package com.cqut.service.util.fileManage.excel;

/**
 * 在导入导出整数类型时的格式化工具类，继承至IExcelDataFormater接口，
 * 主要使用在从xls导入到Collections中时，读取到的整数值的表现形式是.0的double数值，在导入时会调用该importFormat进行处理
 * 可以自已从IExcelDataFormater派生类，以获得自定义的导入导出效果
 * */
public class IntegerFormater implements IExcelDataFormater {

	@Override
	public Object exportFormat(Object obj) {
		return obj;
	}

	@Override
	public Object importFormat(Object obj) {
		if (obj instanceof Double) 
		{
			Double doublev = (Double)obj;
			return doublev.intValue();
		}else
		{
			return null;
		}
	}

}
