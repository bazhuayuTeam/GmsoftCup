package com.cqut.service.util.fileManage.excel;

public class ExcelPointPosition 
{
	ExcelRowPosition 	rowPosition 		= null;
	ExcelColumnPosition columnPosition  = null;
	ExcelPointPosition(ExcelRowPosition 	rowPosition,ExcelColumnPosition columnPosition) throws Exception
	{
		this.rowPosition = rowPosition; 
		this.columnPosition = columnPosition;
	}
	ExcelPointPosition(int rowIndex,String columnName) throws Exception
	{

		this.rowPosition = new ExcelRowPosition(rowIndex);
		this.columnPosition = new ExcelColumnPosition(columnName);
	}
	public ExcelRowPosition getRowPosition() {
		return rowPosition;
	}
	public void setRowPosition(ExcelRowPosition rowPosition) {
		this.rowPosition = rowPosition;
	}
	public ExcelColumnPosition getColumnPosition() {
		return columnPosition;
	}
	public void setColumnPosition(ExcelColumnPosition columnPosition) {
		this.columnPosition = columnPosition;
	}
	
}
