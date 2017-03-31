package com.cqut.service.util.fileManage.excel;


public class ExcelCellPosition 
{
	ExcelSheetPosition sheetPosition = null;
	ExcelRowPosition 	rowPosition 		= null;
	ExcelColumnPosition columnPosition  = null;
	ExcelCellPosition(ExcelSheetPosition sheetPosition,ExcelRowPosition 	rowPosition,ExcelColumnPosition columnPosition) throws Exception
	{
		this.sheetPosition = sheetPosition;
		this.rowPosition = rowPosition; 
		this.columnPosition = columnPosition;
	}
	ExcelCellPosition(int sheetIndex,int rowIndex,String columnName) throws Exception
	{
		this.sheetPosition = new ExcelSheetPosition(sheetIndex);
		this.rowPosition = new ExcelRowPosition(rowIndex);
		this.columnPosition = new ExcelColumnPosition(columnName);
	}
	public ExcelSheetPosition getSheetPosition() {
		return sheetPosition;
	}
	public void setSheetPosition(ExcelSheetPosition sheetPosition) {
		this.sheetPosition = sheetPosition;
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
