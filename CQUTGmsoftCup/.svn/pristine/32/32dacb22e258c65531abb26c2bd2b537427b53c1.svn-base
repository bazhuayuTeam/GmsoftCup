package com.cqut.service.util.fileManage.excel;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;

/**
 * Excel公式类，在导出到excel时，可以向writecell传公式值。 具体内容与在Excle表格中输入的字符串相似 只是需要去掉前导的等于号=
 * 如：在Excel中的公式是：=TODAY() 则 new ExcelFormula("TODAY()");
 * */
public class ExcelFormula
{
	private String			formulaStr			= null;
	private HSSFWorkbook	belongsToWorkBook	= null;
	private Cell			belongsToCell		= null;
	
	public ExcelFormula(String formulaStr, HSSFWorkbook belongsToWorkBook, Cell belongsToCell)
	{
		this.formulaStr = formulaStr;
		this.belongsToWorkBook = belongsToWorkBook;
		this.belongsToCell = belongsToCell;
	}
	
	public ExcelFormula(String formulaStr)
	{
		this.formulaStr = formulaStr;
	}
	
	/**
	 * @param formulaStr
	 *            the formulaStr to set
	 */
	public void setFormulaStr(String formulaStr)
	{
		this.formulaStr = formulaStr;
	}
	
	/**
	 * @return the formulaStr
	 */
	public String getFormulaStr()
	{
		return this.formulaStr;
	}
	
	public HSSFWorkbook getBelongsToWorkBook()
	{
		return this.belongsToWorkBook;
	}
	
	public void setBelongsToWorkBook(HSSFWorkbook belongsToWorkBook)
	{
		this.belongsToWorkBook = belongsToWorkBook;
	}
	
	public Cell getBelongsToCell()
	{
		return this.belongsToCell;
	}
	
	public void setBelongsToCell(Cell belongsToCell)
	{
		this.belongsToCell = belongsToCell;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 * 返回该公式对象的计算公式
	 */
	@Override
	public String toString()
	{
		return this.formulaStr;
	}
	
	/**
	 * 计算并返回该公式对象的值，注意该公式对象的所属XLS对象文档和所属单元格对象不能为空，否则无法计算
	 * @return 计算得到的值
	 */
	public Object evaluator()
	{
		//		CellReference cell = new CellReference("B3");
		if((this.belongsToWorkBook == null) || (this.belongsToCell == null))
		{
			return null;
		}
		Object result = null;
		// FormulaEvaluator evaluator =
		// this.belongsToWorkBook.getCreationHelper().createFormulaEvaluator();
		// evaluator.evaluateFormulaCell(belongsToCell );//返回公式结果的类型
		// -1表示指定单元格不是公式
		// evaluator.evaluateInCell(arg0);//将结果值写入cell替代公式，并返回该公式 。
		HSSFFormulaEvaluator evaluator = new HSSFFormulaEvaluator(this.belongsToWorkBook);
		CellValue cellValue = evaluator.evaluate(this.belongsToCell);// 返回一个新值，并不影响cell。
		switch(cellValue.getCellType())
		{
			case Cell.CELL_TYPE_BOOLEAN :
				result = cellValue.getBooleanValue();
				break;
			case Cell.CELL_TYPE_NUMERIC :
				result = cellValue.getNumberValue();
				break;
			case Cell.CELL_TYPE_STRING :
				result = cellValue.getStringValue();
				break;
			case Cell.CELL_TYPE_BLANK :
				result = null;
				break;
			case Cell.CELL_TYPE_ERROR :
				//				System.err.println("发生错误");
				break;
			case Cell.CELL_TYPE_FORMULA :
				//				System.err.println("发生错误");
				break;
			default :
				//				System.err.println("发生错误");
				break;
		}
		return result;
	}
}
