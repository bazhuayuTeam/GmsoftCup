package com.cqut.service.util.fileManage.excel;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Controller;


@Controller
@RemoteProxy
public class ExcelOperater 
{
	private HSSFWorkbook	workBook			= null;
	private File			asscoationedFile	= null;
	public boolean create(File file) throws IOException
	{
		this.workBook = new HSSFWorkbook();// 建立新HSSFWorkbook对象
		this.asscoationedFile = file;
		boolean createresult = file.createNewFile();
		if(createresult)
		{
			return save();
		}
		else 
		{
			return false;
		}
	}
	public boolean open(File file) throws IOException
	{
		this.asscoationedFile = file;
		InputStream input = new FileInputStream(asscoationedFile);
		POIFSFileSystem fs = new POIFSFileSystem(input);
		this.workBook = new HSSFWorkbook(fs);
		return true;
	}
	public boolean save() throws IOException
	{
		FileOutputStream fileOut = new FileOutputStream(asscoationedFile, false);
		workBook.write(fileOut);
		fileOut.close();
		return true;
	}
	public boolean saveAs(File file) throws IOException
	{
		boolean createResult = true;
		if(!file.exists())
		{
			createResult = file.createNewFile();
		}
		if(createResult)
		{
			FileOutputStream fileOut = new FileOutputStream(file, false);
			workBook.write(fileOut);
			fileOut.close();
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean loadTemplate(File file) throws IOException
	{
		InputStream input = new FileInputStream(file);
		POIFSFileSystem fs = new POIFSFileSystem(input);
		this.workBook = new HSSFWorkbook(fs);
		return true;
	}
	public boolean writeCell(Object sourceObj,ExcelCellPosition excelPosition)
	{
		return writeCell(sourceObj,null,excelPosition, null,true);
	}
	public boolean writeCell(Object sourceObj,IExcelDataFormater objFormater,ExcelCellPosition excelPosition)
	{
		return writeCell(sourceObj,objFormater,excelPosition, null,true);
	}
	public boolean writeCell(Object sourceObj,IExcelDataFormater objFormater,ExcelCellPosition excelPosition, CellStyle cellStyle, boolean autoSize)
	{
		int rowPosition = excelPosition.getRowPosition().getRowIndex();
		int columnPosition = excelPosition.getColumnPosition().getColumnIndex();
		ExcelSheetPosition sheetPosition = excelPosition.getSheetPosition();
		Integer sheetIndexValue = sheetPosition.getSheetIndex();
		int sheetIndex = 0;
		if(sheetIndexValue != null)
		{
			sheetIndex = sheetIndexValue;
		}
		else
		{
			String sheetName = sheetPosition.getSheetName();
			if(sheetName != null)
			{
				sheetIndex = workBook.getSheetIndex(sheetName);
			}
		}
		Object obj = sourceObj;
		if(objFormater != null)
		{
			obj = objFormater.exportFormat(sourceObj);
		}
		int sheetNum = workBook.getNumberOfSheets();
		while(sheetNum <= sheetIndex)
		{
			workBook.createSheet();
			sheetNum++;
		}
		HSSFSheet sheet = workBook.getSheetAt(sheetIndex);
		HSSFRow row = sheet.getRow(rowPosition-1);
		if (row == null) {
			row = sheet.createRow(rowPosition-1);
		}
		HSSFCell cell = row.getCell(columnPosition-1);
		if (cell == null) {
			cell = row.createCell(columnPosition-1);
		}
		if (cellStyle != null) {
			cell.setCellStyle(cellStyle);
		}
		if (autoSize == true) {
			sheet.autoSizeColumn(columnPosition-1);
		} else {
			sheet.setColumnWidth(columnPosition-1, 2500);
		}
		if (obj == null) {
			cell.setCellType(Cell.CELL_TYPE_BLANK);
			return true;
		} else if (obj.getClass().equals(String.class)) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(obj.toString());
			return true;
		} else if (obj instanceof Integer) {
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			Integer value = (Integer) obj;
			cell.setCellValue(value.intValue());
			return true;
		} else if (obj instanceof Float) {
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			Float value = (Float) obj;
			cell.setCellValue(value.floatValue());
			return true;
		} else if (obj instanceof Double) {
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			Double value = (Double) obj;
			cell.setCellValue(value.doubleValue());
			return true;
		} else if (obj instanceof Boolean) {
			cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
			Boolean value = (Boolean) obj;
			cell.setCellValue(value);
			return true;
		} else if (obj instanceof Date) {
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			if (cellStyle == null) {
				cellStyle = workBook.createCellStyle();
			}
			short df = workBook.createDataFormat().getFormat("yyyy-mm-dd HH:mm:ss");
			cellStyle.setDataFormat(df);
			Date value = (Date) obj;
			cell.setCellValue(value);
			cell.setCellStyle(cellStyle);
			// sheet.setColumnWidth(position.y, 5000);
			return true;
			// HSSFDateUtil.isCellDateFormatted();
		} else if (obj instanceof java.sql.Date) {
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			if (cellStyle == null) {
				cellStyle = workBook.createCellStyle();
			}
			short df = workBook.createDataFormat().getFormat("yyyy-mm-dd HH:mm:ss");
			cellStyle.setDataFormat(df);
			java.sql.Date value = (java.sql.Date) obj;
			cell.setCellValue(value);
			cell.setCellStyle(cellStyle);
			// sheet.setColumnWidth(position.y, 5000);
			return true;
		} else if(obj instanceof ExcelFormula)
		{
			cell.setCellType(Cell.CELL_TYPE_FORMULA);
			cell.setCellFormula(((ExcelFormula)obj).getFormulaStr());
			return true;
		}
		else 
		{
			return false;
		}
	}
	@SuppressWarnings("deprecation")
	public boolean mergeCell(ExcelSheetPosition excelSheetPosition,ExcelPointPosition startPoint,ExcelPointPosition endPoint)
	{
		HSSFSheet sheet = null;
		Integer sheetIndexValue = excelSheetPosition.getSheetIndex();
		int sheetIndex = 0;
		if(sheetIndexValue != null)
		{
			sheetIndex = sheetIndexValue;
		}
		else
		{
			String sheetName = excelSheetPosition.getSheetName();
			if(sheetName != null)
			{
				sheetIndex = workBook.getSheetIndex(sheetName);
			}
		}
		try {
			sheet = workBook.getSheetAt(sheetIndex);
		} catch (Exception e) {
			sheet = workBook.createSheet();
		}
		int startRowPosition = startPoint.getRowPosition().getRowIndex();
		int endRowPosition = endPoint.getRowPosition().getRowIndex();
		int startColumnPosition = startPoint.getColumnPosition().getColumnIndex();
		int endColumnPosition = endPoint.getColumnPosition().getColumnIndex();
		sheet.addMergedRegion(new Region(startRowPosition, (short) startColumnPosition,
				endRowPosition, (short) endColumnPosition));
//		if (cellStyle == null) {
//			cellStyle = wb.createCellStyle();
//			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
//			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//			HSSFFont font = wb.createFont();
//			font.setFontHeightInPoints((short) 16);
//			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//			cellStyle.setFont(font);
//		}
//		writeCell(wb, obj,objFormater,sheetIndex, startPoint, cellStyle, autoSize);
		return true;
	}
	
//	2012-04-24 15:48 POI判断指定的单元格是否是合并单元格
//	public boolean isMergedRegion(HSSFSheet sheet,int row ,int column)
//	{
//	  int sheetMergeCount = sheet.getNumMergedRegions();
//	  for (int i = 0; i < sheetMergeCount; i++) {
//	   Region ca = sheet.getMergedRegionAt(i);
//	   int firstColumn = ca.getColumnFrom();
//	   int lastColumn = ca.getColumnTo();
//	   int firstRow = ca.getRowFrom();
//	   int lastRow = ca.getRowTo();
//	   if(row >= firstRow && row <= lastRow)
//	   {
//	    if(column >= firstColumn && column <= lastColumn)
//	    {
//	     return true;
//	    }
//	   }
//	  }
//	  return false;
//	 }

	
	public boolean writeCells(Object[] objs, ExcelCellPosition[] excelPosition)
	{
		return false;
	}
	public boolean writeCells(Object[] objs,IExcelDataFormater[] objFormater, ExcelCellPosition[] excelPosition,CellStyle[] cellStyle,boolean[] autoSize)
	{
		return false;
	}
	public boolean writeCells(Map<String, Object> map,String[] columnOrder,Map<String,IExcelDataFormater> columnFormaters,Map<String,CellStyle> cellStyles,Map<String,Boolean> autoSizes,ExcelSheetPosition excelSheetPosition,ExcelRowPosition startRow,Map<String,ExcelColumnPosition> excelColumnPositions) throws Exception
	{
		for (String column : columnOrder) 
		{
			IExcelDataFormater formater = null;
			if(columnFormaters != null)
			{
				formater = columnFormaters.get(column);
			}
			CellStyle cellStyle = null;
			if(cellStyles != null)
			{
				cellStyle = cellStyles.get(column);
			}
			Boolean autoSize = true;
			if(autoSizes != null)
			{
				autoSize  = autoSizes.get(column);
			}
			Object obj = map.get(column);
			ExcelColumnPosition columnPosition = null;
			if(excelColumnPositions!= null)
			{
				columnPosition = excelColumnPositions.get(column);
			}
			else
			{
				return false;
			}
			ExcelCellPosition excelPosition = new ExcelCellPosition(excelSheetPosition, startRow, columnPosition);
			writeCell(obj,formater,excelPosition, cellStyle,autoSize);
		}
		return false;
	}
	public boolean writeCells(Collection<Map<String, Object>> collection,String[] columnOrder,ExcelCellPosition startPosition)
	{
		return false;
	}
	public boolean writeCells(Collection<Map<String, Object>> collection,String[] columnOrder,ExcelSheetPosition excelSheetPosition,ExcelRowPosition startRow,Map<String,ExcelColumnPosition> excelColumnPositions)
	{
		return false;
	}
	
	public boolean writeCells(Collection<Map<String, Object>> collection,String[] columnOrder,Map<String,IExcelDataFormater> columnFormaters,Map<String,CellStyle> cellStyles,Map<String,Boolean> autoSizes,ExcelSheetPosition excelSheetPosition,ExcelRowPosition startRow,Map<String,ExcelColumnPosition> excelColumnPositions, boolean showRowNum,ExcelColumnPosition rowNumColumnPosition,IExcelDataFormater rowNumColumnFormater) throws Exception
	{
		Iterator<Map<String, Object>> ir = collection.iterator();
		Map<String, Object> map = null;
		
		int i = 0;
		while (ir.hasNext()) {
			i++;
			if (showRowNum) {
				ExcelCellPosition excelPosition = new ExcelCellPosition(excelSheetPosition, startRow, rowNumColumnPosition);
				writeCell(i,rowNumColumnFormater,excelPosition , null,true);
			}
			map = ir.next();
			writeCells(map,columnOrder,columnFormaters ,cellStyles,autoSizes,excelSheetPosition,startRow,excelColumnPositions);
		}
		return showRowNum;

	}
	
	public Collection<Map<String, Object>> getDataCollection(String[] columnOrder,Map<String,IExcelDataFormater> columnFormaters,ExcelSheetPosition sheet,ExcelRowPosition startRow,ExcelColumnPosition[] needColumnPositions)
	{
		return null;
	}
	
}
