package com.cqut.service.util.fileManage.excel;

/**
 * 在导入导出性别时的格式化类，继承至IExcelDataFormater接口，
 * 可在导出时将比尔值
 * true格式化为字符串“男”；
 * false格式化为字符串“女”;
 * 导入时相反
 * */
public class SexFormater implements IExcelDataFormater
{
	@Override
	public Object exportFormat(Object obj)
	{
		if (obj instanceof Boolean) 
		{
			Boolean bool = (Boolean)obj;
			if(bool)
			{
				return "男";
			}else
			{
				return "女";
			}
		}else
		{
			return obj;
		}
	}
	@Override
	public Object importFormat(Object obj)
	{
		if (obj instanceof String) 
		{
			
			if(obj.equals("男"))
			{
				return true;
			}else if(obj.equals("女"))
			{
				return false;
			}
			else 
			{
				return null;
			}
		}else
		{
			return null;
		}
	}
}
