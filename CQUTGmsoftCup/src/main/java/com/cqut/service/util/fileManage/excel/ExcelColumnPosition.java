package com.cqut.service.util.fileManage.excel;

public class ExcelColumnPosition {

	Integer columnIndex = null;
	String columnName = null;
	public ExcelColumnPosition(int columnIndex)
	{
		this.columnIndex = columnIndex;
		this.columnName  = getColumnName(columnIndex);
	}
	public ExcelColumnPosition(String columnName) throws Exception
	{
		if(verifyColumnName(columnName))
		{
			this.columnName = columnName;
			this.columnIndex  = getColmnIndex(columnName);
		}
		else 
		{
			throw new Exception("列名是由26个英文字母组成的字符串，请检查格式");
		}
		
	}
	public static boolean verifyColumnName(String columnName)
	{
		//"^[A-Za-z]+$ 	匹配由26个英文字母组成的字符串
		//^[A-Z]+$　　//匹配由26个英文字母的大写组成的字符串 
		//^[a-z]+$　　//匹配由26个英文字母的小写组成的字符串 
		String regex = "^[A-Za-z]+$";
		if(!columnName.matches(regex))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	private static int getColmnIndex(String columnName)
	{
		int resultValue = 0;
		columnName = columnName.toUpperCase();
		for(int i = 0;i<columnName.length();i++)
		{
			char c = columnName.charAt(i);
			int value = (int)c-64;
			resultValue += value * Math.pow(26, columnName.length()-i-1);
		}
		return resultValue;
	}
	private static String getColumnName(int columnValue)
	{
	    StringBuilder columnName = new StringBuilder();
        int n = columnValue;
        while (n != 0)
        {
            if (n % 26 == 0)
            {
            	columnName.insert(0, 'Z');
                n = n / 26 - 1;
            }
            else
            {
            	columnName.insert(0, (char)((n % 26 - 1) + 'A'));
                n /= 26;
            }
        }
        return columnName.toString();
	}
	public Integer getColumnIndex() {
		return columnIndex;
	}
	public void setColumnIndex(Integer columnIndex) {
		this.columnIndex = columnIndex;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
}
