package com.cqut.service.util.fileManage.excel;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.io.FileTransfer;
import org.springframework.stereotype.Controller;

import com.cqut.util.fileUtil.FileUtil;

/**
 * Excel导入导出的通用类，主要做Collection<Map<String,Object>>与Excel文档对象HSSFWorkbook的转化。
 */
@Controller
@RemoteProxy
public class ExcelService {

	/**
	 * 集合类对象转化为XLS文档对象
	 * 
	 * @param HSSFWorkbook
	 *            wb Excel文档对象
	 * @param Collection
	 *            <Map<String, Object>> collection 要导出的包含数据的对象
	 * @param String
	 *            [] columnOrder collection对象中存放的Map中的Key值顺序（因为MAP是无序的）
	 * @param int sheetIndex 指定要写入数据的sheet页序号，从0开始
	 * @param Point
	 *            startPosition 开始输出数据的地方，位置是在数据左上角
	 * @param boolean showRowNum 是否显示序号（序号从1开始）。
	 * @param IExcelDataFormater rowNumFormater 序号列的格式化函数
	 *  如需将Excel文档对象保存到文件，@see
	 *        toXlsFile 和 toTempXlsFile
	 *        
	 * */
	/**
	 * 集合类对象转化为XLS文档对象
	 * @param wb Excel文档对象
	 * @param collection 要导出的包含数据的对象
	 * @param columnOrder collection对象中存放的Map中的Key值顺序（因为MAP是无序的）
	 * @param columnFormaters columnOrder字符串数组对应的格式化函数
	 * @param sheetIndex 指定要写入数据的sheet页序号，从0开始
	 * @param startPosition 开始输出数据的地方，位置是在数据左上角
	 * @param showRowNum 是否显示序号（序号从1开始）。
	 * @param rowNumFormater 序号的格式化函数
	 */
	public static void collectionToXls(HSSFWorkbook wb,
			Collection<Map<String, Object>> collection, String[] columnOrder, CellStyle cellStyle ,
			Map<String, IExcelDataFormater> columnFormaters, int sheetIndex,
			Point startPosition, boolean showRowNum,
			IExcelDataFormater rowNumFormater) {
		Iterator<Map<String, Object>> ir = collection.iterator();
		Map<String, Object> map = null;
		Point p = new Point(0, 0);
		p.x = startPosition.x;
		p.y = startPosition.y;
		int i = 0;
		while (ir.hasNext()) {
			i++;
			p.y = startPosition.y;
			if (showRowNum) {
				ExcelService.writeCell(wb, i, rowNumFormater, sheetIndex, p, cellStyle, true);
				p.y++;
			}
			map = ir.next();
			for (String column : columnOrder) {
				IExcelDataFormater formater = null;
				if (columnFormaters != null) {
					formater = columnFormaters.get(column);
				}
				Object obj = map.get(column);
				ExcelService.writeCell(wb, obj, formater, sheetIndex, p, cellStyle, true);
				p.y++;
			}
			p.x++;
		}
		
	}
	
	public static void collectionToXls(HSSFWorkbook wb,
			Collection<Map<String, Object>> collection, String[] columnOrder,Map<String,IExcelDataFormater> columnFormaters,int sheetIndex,
			Point startPosition, boolean showRowNum,IExcelDataFormater rowNumFormater) {
		Iterator<Map<String, Object>> ir = collection.iterator();
		Map<String, Object> map = null;
		Point p = new Point(0, 0);
		p.x = startPosition.x;
		p.y = startPosition.y;
		int i = 0;
		while (ir.hasNext()) {
			i++;
			p.y = startPosition.y;
			if (showRowNum) {
				writeCell(wb, i,rowNumFormater,sheetIndex, p, null, true);
				p.y++;
			}
			map = ir.next();
			for (String column : columnOrder) {
				IExcelDataFormater formater = null;
				if(columnFormaters != null)
				{
					formater = columnFormaters.get(column);
				}
				Object obj = map.get(column);
				writeCell(wb, obj,formater,sheetIndex, p, null, true);
				p.y++;
			}
			p.x++;
		}

	}

	/**
	 * XLS文档对象转化为集合类对象
	 * 
	 * @param HSSFWorkbook
	 *            wb Excel文档对象
	 * @param String
	 *            [] columnOrder
	 *            需要的Excel列与Map的Key值对应的顺序，对象中存放的Map中的Key值顺序（因为MAP是无序的）
	 * @param Map<String,IExcelDataFormater> formaters 指定columnOrder中的列对应的格式化函数，可为null表式不进行格式化。
	 * @param int sheetIndex 指定要读取的行sheet页
	 * @param int sheetLine 指定开始读取的行（0表示第一行） ,即Excel表格左边的行号
	 * @param short[] needRow指定ColumnOrder对应的需要的（从左向右从0开始）列（须要一一对应，包括顺序）
	 * @return LinkedList<Map<String, Object>> 返回的集合类对象
	 * */
	/*
	public static LinkedList<Map<String, Object>> xlsToCollection(
			HSSFWorkbook wb, String[] columnOrder, Map<String,IExcelDataFormater> columnFormaters,int sheetIndex,
			int startLine, short[] needRow) {
		LinkedList<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
		HSSFSheet sheet = null;
		try {
			sheet = wb.getSheetAt(sheetIndex);
		} catch (Exception e) {
			return null;
		}
		Iterator<Row> rows = sheet.rowIterator();
		int lineNum = 0;
		while (rows.hasNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			Row row = rows.next();
			if (lineNum < startLine) {
				lineNum++;
				continue;
			}
			Iterator<Cell> cells = row.cellIterator();
			int rowNum = 0;
			int z = 0;
			while (cells.hasNext()) {
				Cell cell = cells.next();
				if (z >= needRow.length) {
					break;
				}
				if (z >= columnOrder.length) {
					break;
				}
				if (rowNum != needRow[z]) {
					rowNum += 1;
					continue;
				} else {
					rowNum += 1;
					z++;
				}
				
				IExcelDataFormater format = null;
				if(columnFormaters != null)
				{
					format = columnFormaters.get(columnOrder[z - 1]);
				}
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						Date date = cell.getDateCellValue();
						if(format != null)
						{
							Object obj = format.importFormat(date);
							map.put(columnOrder[z - 1], obj);
						}
						else
						{
							map.put(columnOrder[z - 1], date);
						}
					} else {
						double value = cell.getNumericCellValue();
						if(format != null)
						{
							Object obj = format.importFormat(value);
							map.put(columnOrder[z - 1], obj);
						}
						else
						{
							map.put(columnOrder[z - 1], value);
						}
					}
					break;
				case Cell.CELL_TYPE_STRING:
					String value1 = cell.getStringCellValue();
					if(format != null)
					{
						Object obj = format.importFormat(value1);
						map.put(columnOrder[z - 1], obj);
					}
					else
					{
						map.put(columnOrder[z - 1], value1);
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					Boolean value2 = cell.getBooleanCellValue();
					if(format != null)
					{
						Object obj = format.importFormat(value2);
						map.put(columnOrder[z - 1], obj);
					}
					else
					{
						map.put(columnOrder[z - 1], value2);
					}
					break;
				case Cell.CELL_TYPE_FORMULA:
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						// 如果是date类型则 ，获取该cell的date值
						Date date = cell.getDateCellValue();
						if(format != null)
						{
							Object obj = format.importFormat(date);
							map.put(columnOrder[z - 1], obj);
						}
						else
						{
							map.put(columnOrder[z - 1], date);
						}
					}
//					else
//					{
//						String formulaStr = cell.getCellFormula();
//						if(format != null)
//						{
//							Object obj = format.importFormat(formulaStr);
//							map.put(columnOrder[z - 1], obj);
//						} else {
//							map.put(columnOrder[z - 1], formulaStr);
//						}
//					}
					else {
						Double value = cell.getNumericCellValue();
						if(format != null)
						{
							Object obj = format.importFormat(value);
							map.put(columnOrder[z - 1], obj);
						}
						else
						{
							map.put(columnOrder[z - 1], value);
						}
					}
					break;
				case Cell.CELL_TYPE_BLANK:
					if(format != null)
					{
						Object obj = format.importFormat(null);
						map.put(columnOrder[z - 1], obj);
					}
					else
					{
						map.put(columnOrder[z - 1], null);
					}
					break;
				default:
					// System.out.print("未知类型"+ cell.getCellType() + "\t");
					break;
				}
			}
			result.add(map);
		}
		return result;
	}
	*/
	
	/**
	 * XLS文档对象转化为集合类对象
	 * @param wb Excel文档对象
	 * @param columnOrder 需要的Excel列与Map的Key值对应的顺序，对象中存放的Map中的Key值顺序（因为MAP是无序的）
	 * @param columnFormaters columnOrder中的列对应的格式化函数，可为null表式不进行格式化。
	 * @param sheetIndex 要读取的行sheet页
	 * @param startLine 指定开始读取的行（0表示第一行） ,即Excel表格左边的行号
	 * @param needRow needRow指定ColumnOrder对应的需要的（从左向右从0开始）列（须要一一对应，包括顺序）
	 * @return 读取到的值
	 */
	public static LinkedList<Map<String, Object>> xlsToCollection(
			HSSFWorkbook wb, String[] columnOrder,
			Map<String, IExcelDataFormater> columnFormaters, int sheetIndex,
			int startLine, short[] needRow,IStopReadFlag stopReadFlag) {
		Arrays.sort(needRow);
		LinkedList<Map<String, Object>> result = new LinkedList<Map<String, Object>>();
		HSSFSheet sheet = wb.getSheetAt(sheetIndex);
		Iterator<Row> rows = sheet.rowIterator();
		while (rows.hasNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			Row row = rows.next();
			if (row.getRowNum() < startLine) {
				continue;
			}
			Iterator<Cell> cells = row.cellIterator();
			int z = 0;
			while (cells.hasNext()) {
				Cell cell = cells.next();
				if ((z >= needRow.length) || (z >= columnOrder.length)) {
					break;
				}
				if (cell.getColumnIndex() != needRow[z]) {
					continue;
				} else {
					z++;
				}
				IExcelDataFormater format = null;
				if (columnFormaters != null) {
					format = columnFormaters.get(columnOrder[z - 1]);
				}
				map.put(columnOrder[z - 1], ExcelService.readCell(cell,format));
			}
			if((stopReadFlag != null) && (stopReadFlag.isEnd(map) == true))
			{
				return result;
			}
			else
			{
				result.add(map);
			}
			
		}
		return result;
	}
	
	/**
	 * 读取Cell的单元格的值
	 * @param cell 要读取的单元格的值
	 * @param format 用于将读取到的值进行转换后再返回，为空表示不转换
	 * @return 单元格的值
	 */
	public static Object readCell(Cell cell,IExcelDataFormater format)
	{
		Object cellValue = null;
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				cellValue = DateUtil.isCellDateFormatted(cell)?cell.getDateCellValue():cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_STRING:
				cellValue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				cellValue = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA:
				cellValue = new ExcelFormula(cell.getCellFormula(),(HSSFWorkbook)cell.getSheet().getWorkbook(),cell);
				break;
			case Cell.CELL_TYPE_BLANK:
				cellValue = null;
				break;
			default:
				break;
		}
		if (format != null) {
			cellValue = format.importFormat(cellValue);
		}
		return cellValue;
	}
	
	/**
	 * 读取Cell的单元格的值
	 * @param wb 文档对象
	 * @param sheetIndex 单元格所在页
	 * @param cellPosition 单元格位置
	 * @param format 用于将读取到的值进行转换后再返回，为空表示不转换
	 * @return
	 */
	public static Object readCell(HSSFWorkbook wb,int sheetIndex,Point cellPosition,IExcelDataFormater format)
	{

		HSSFSheet  sheet= wb.getSheetAt(sheetIndex);
		if(sheet == null)
		{
			return null;
		}
		HSSFRow row = sheet.getRow(cellPosition.x);
		if (row == null) {
			return null;
		}
		HSSFCell cell = row.getCell(cellPosition.y);
		if (cell == null) {
			return null;
		}
		return readCell(cell,format);
	}

	/**
	 * 指定一个区域，进行单元格合并，并填充相应内容,默认的样式为居中，字体大小为16，可通过cellStyle自定 可用于向文档对象中写入标题，如：
	 * {@code ExcelService.merginAndWirteCell(wb,"标题",new Point(0,0),new
	 * Point(3,3),null,true);}
	 * 
	 * @param HSSFWorkbook
	 *            wb Excel文档对象
	 * @param Object
	 *            obj 要写入的对象，@see writeCell
	 * @param int sheetIndex 指定要写入数据的sheet页序号，从0开始
	 * @param Point
	 *            startPoint 区域左上角（0开始）
	 * @param Point
	 *            endPoint 区域右上角坐标（0开始）
	 * @param CellStyle
	 *            cellStyle 单元格样式
	 * @param boolean autoSize 自动宽度
	 * @see writeCell
	 * */
	@SuppressWarnings("deprecation")
	public static void merginAndWirteCell(HSSFWorkbook wb, Object obj,IExcelDataFormater objFormater,int sheetIndex,
			Point startPoint, Point endPoint, CellStyle cellStyle,
			boolean autoSize) {
		HSSFSheet sheet = null;
		try {
			sheet = wb.getSheetAt(wb.getActiveSheetIndex());
		} catch (Exception e) {
			sheet = wb.createSheet();
		}
		sheet.addMergedRegion(new Region(startPoint.x, (short) startPoint.y,
				endPoint.x, (short) endPoint.y));
		if (cellStyle == null) {
			cellStyle = wb.createCellStyle();
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			HSSFFont font = wb.createFont();
			font.setFontHeightInPoints((short) 16);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyle.setFont(font);
		}
		writeCell(wb, obj,objFormater,sheetIndex, startPoint, cellStyle, autoSize);
	}

	/**
	 * 指定一个起点，对象数组依次填充到单元格（目前支持基本的类型@see writeCell），,默认的样式为 居中，可通过cellStyle自定
	 * 可用于向文档对象中写入列标题，如：{@code String[] tableheaders = "姓名","年龄"};
	 * ExcelService.writeStrAryOnCells(wb,tableheaders,new Point(2,0));}
	 * 
	 * @param HSSFWorkbook
	 *            wb Excel文档对象
	 * @param Object
	 *            [] tableheaders 对象数组，@see writeCell
	 * @parma int sheetIndex	写入表页，0表示第一页
	 * @param Point
	 *            startPoint 开始位置，从左向右
	 * @param CellStyle
	 *            cellStyle 单元格样式
	 * @param boolean autoSize 自动宽度
	 * */
	public static void writeObjectAryOnCells(HSSFWorkbook wb, Object[] objs,IExcelDataFormater objFormater,int sheetIndex,
			Point point, CellStyle cellStyle, boolean autoSize) {
		Point p = new Point();
		p.x = point.x;
		p.y = point.y;
		if (cellStyle == null) {
			cellStyle = wb.createCellStyle();
			cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		}
		for (Object obj : objs) {
			writeCell(wb, obj,objFormater, sheetIndex,p, cellStyle, autoSize);
			p.y++;
		}
	}

	/**
	 * 在指定位置的单元格写数据，可自定单元格样式，自动单元格宽度。
	 * 指定一个区域，进行单元格全并，并填充相应内容,默认的样式为居中，字体大小为16，可通过cellStyle自定 可用于向文档对象中写入标题，如：
	 * {@code ExcelService.merginAndWirteCell(wb,"标题",new Point(0,0),new
	 * Point(3,3),null,true);}
	 * 
	 * @param HSSFWorkbook
	 *            wb Excel文档对象
	 * @param Object
	 *            obj 要写入的对象，暂支持数字，字符串，布尔值，日期类型的数据，
	 * @param IExcelDataFormater objFormater 写入对象的转换函数，@see IExcelDataFormater
	 * @param int sheetIndex 写入的sheet序号，从0开始
	 * @param Point
	 *            startPoint 写入的位置
	 * @param CellStyle
	 *            cellStyle 单元格样式
	 * @param boolean autoSize 是否自动宽度
	 * @return true 写入成功 false 失败
	 * */
	public static boolean writeCell(HSSFWorkbook wb, Object sourceObj,IExcelDataFormater objFormater,int sheetIndex,
			Point position, CellStyle cellStyle, boolean autoSize) 
	{
		Object obj = sourceObj;
		if(objFormater != null)
		{
			obj = objFormater.exportFormat(sourceObj);
		}
		int sheetNum = wb.getNumberOfSheets();
		while(sheetNum <= sheetIndex)
		{
			wb.createSheet();
			sheetNum++;
		}
		HSSFSheet sheet = wb.getSheetAt(sheetIndex);
		HSSFRow row = sheet.getRow(position.x);
		if (row == null) {
			row = sheet.createRow(position.x);
		}
		HSSFCell cell = row.getCell(position.y);
		if (cell == null) {
			cell = row.createCell(position.y);
		}
		if (cellStyle != null) {
			cell.setCellStyle(cellStyle);
		}
		if (autoSize == true) {
			sheet.autoSizeColumn(position.y);
		} else {
			sheet.setColumnWidth(position.y, 2500);
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
				cellStyle = wb.createCellStyle();
			}
			short df = wb.createDataFormat().getFormat("yyyy-mm-dd HH:mm:ss");
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
				cellStyle = wb.createCellStyle();
			}
			short df = wb.createDataFormat().getFormat("yyyy-mm-dd HH:mm:ss");
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
		else {
			 System.err.println("插入位置:" + position.toString() +
			 "失败!原因是不支持的类型:"
			 + obj.getClass().toString());
			return false;
		}
	}
	
	//可指定单元格的列宽
	public static boolean writeCellSetCellWidth(HSSFWorkbook wb, Object sourceObj,IExcelDataFormater objFormater,int sheetIndex,
			Point position, CellStyle cellStyle,int cellWidth) 
	{
		Object obj = sourceObj;
		if(objFormater != null)
		{
			obj = objFormater.exportFormat(sourceObj);
		}
		int sheetNum = wb.getNumberOfSheets();
		while(sheetNum <= sheetIndex)
		{
			wb.createSheet();
			sheetNum++;
		}
		HSSFSheet sheet = wb.getSheetAt(sheetIndex);
		HSSFRow row = sheet.getRow(position.x);
		if (row == null) {
			row = sheet.createRow(position.x);
		}
		HSSFCell cell = row.getCell(position.y);
		if (cell == null) {
			cell = row.createCell(position.y);
		}
		if (cellStyle != null) {
			cell.setCellStyle(cellStyle);
		}
		if(cellWidth!=0){
			sheet.setColumnWidth(position.y, cellWidth);
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
				cellStyle = wb.createCellStyle();
			}
			short df = wb.createDataFormat().getFormat("yyyy-mm-dd HH:mm:ss");
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
				cellStyle = wb.createCellStyle();
			}
			short df = wb.createDataFormat().getFormat("yyyy-mm-dd HH:mm:ss");
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
		else {
			 System.err.println("插入位置:" + position.toString() +
			 "失败!原因是不支持的类型:"
			 + obj.getClass().toString());
			return false;
		}
	}
	
	//可指定单元格的列宽,也可指定sheet的名字
	public static boolean writeCellSetCellWidth(HSSFWorkbook wb, Object sourceObj,IExcelDataFormater objFormater,int sheetIndex,
			Point position, CellStyle cellStyle,int cellWidth,String sheetName) 
	{
		Object obj = sourceObj;
		if(objFormater != null)
		{
			obj = objFormater.exportFormat(sourceObj);
		}
		int sheetNum = wb.getNumberOfSheets();
		while(sheetNum <= sheetIndex)
		{
			wb.createSheet(sheetName);
			sheetNum++;
		}
		HSSFSheet sheet = wb.getSheetAt(sheetIndex);
		wb.setSheetName(sheetIndex, sheetName);
		HSSFRow row = sheet.getRow(position.x);
		if (row == null) {
			row = sheet.createRow(position.x);
		}
		HSSFCell cell = row.getCell(position.y);
		if (cell == null) {
			cell = row.createCell(position.y);
		}
		if (cellStyle != null) {
			cell.setCellStyle(cellStyle);
		}
		if(cellWidth!=0){
			sheet.setColumnWidth(position.y, cellWidth);
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
				cellStyle = wb.createCellStyle();
			}
			short df = wb.createDataFormat().getFormat("yyyy-mm-dd HH:mm:ss");
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
				cellStyle = wb.createCellStyle();
			}
			short df = wb.createDataFormat().getFormat("yyyy-mm-dd HH:mm:ss");
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
		else {
			 System.err.println("插入位置:" + position.toString() +
			 "失败!原因是不支持的类型:"
			 + obj.getClass().toString());
			return false;
		}
	}
	
	
	/**
	 * 集合类对象转化为XLS文档对象
	 * @author fm 修正表格样式bug
	 * @see 该方法只适合一般导出,日期输出格式被固定为"yyyy-MM-dd HH:mm:ss"
 	 * @param wb Excel文档对象
	 * @param collection 要导出的包含数据的对象
	 * @param columnOrder collection对象中存放的Map中的Key值顺序
	 * @param cellStyles 每个表格对应的样式可以为null
	 * @param sheetIndex 指定要写入数据的sheet页序号，从0开始
	 * @param startPosition 开始输出数据的地方，位置是在数据左上角
	 * @param showRowNum 是否显示序号（序号从1开始）。
	 * @param autoSize 是否自适应宽度
	 */
	public static void collection2Xls(HSSFWorkbook wb,
			Collection<Map<String, Object>> collection, String[] columnOrder, 
			CellStyle[] cellStyles, int sheetIndex,Point startPosition, boolean showRowNum,boolean autoSize) {
		Iterator<Map<String, Object>> ir = collection.iterator();
		Map<String, Object> map = null;
		int i = 0;
		CellStyle cStyle = null;
		Point p = new Point(0, 0);
		
		p.x = startPosition.x;
		p.y = startPosition.y;
		
		while (ir.hasNext()) {
			i++;
			p.y = startPosition.y;
			
			if (showRowNum) {
				cStyle = (null!=cellStyles ? cellStyles[0] : null);
				ExcelService.writeCell(wb, i, null, sheetIndex, p, cStyle, autoSize);
				p.y++;
			}
			
			map = ir.next();
			for (int k=0;k<columnOrder.length;k++) {
				Object obj = map.get(columnOrder[k])+"";
				cStyle = (null!=cellStyles ? cellStyles[(showRowNum ? k+1 : k)] : null);
				ExcelService.writeCell(wb, obj, null, sheetIndex, p, cStyle, autoSize);
				p.y++;
			}
			p.x++;
		}
	}
	
	//表格中有合并的单元格
	public static void collection2Xls(HSSFWorkbook wb,
			Collection<Map<String, Object>> collection, String[] columnOrder, 
			CellStyle[] cellStyles, int sheetIndex,Point startPosition, boolean showRowNum,boolean autoSize,Map<Integer,Integer> nodes) {
		Iterator<Map<String, Object>> ir = collection.iterator();
		Map<String, Object> map = null;
		int i = 0;
		CellStyle cStyle = null;
		Point p = new Point(0, 0);
		
		p.x = startPosition.x;
		p.y = startPosition.y;
		
		while (ir.hasNext()) {
			i++;
			p.y = startPosition.y;
			
			if (showRowNum) {
				cStyle = (null!=cellStyles ? cellStyles[0] : null);
				ExcelService.writeCell(wb, i, null, sheetIndex, p, cStyle, autoSize);
				p.y++;
			}
			
			map = ir.next();
			for (int k=0;k<columnOrder.length;k++) {
				System.out.println(columnOrder[k]);
				Object obj = map.get(columnOrder[k]);
				cStyle = (null!=cellStyles ? cellStyles[(showRowNum ? k+1 : k)] : null);
			        
				ExcelService.writeCell(wb, obj, null, sheetIndex, p, cStyle, autoSize);
				
				Set<Integer> key = nodes.keySet();
		        for (Iterator it = key.iterator(); it.hasNext();) {
		        	Integer node = (Integer) it.next();
		        	if(node == p.y){
		        		p.y += nodes.get(node);
		        	}else{
		        		p.y++;
		        	}
		            
		        }
			}
			p.x++;
		}
	}
	
	//按模板中每列的固定宽度导出
	public static void collection2Xls2(HSSFWorkbook wb,
			Collection<Map<String, Object>> collection, String[] columnOrder, 
			CellStyle[] cellStyles, int sheetIndex,Point startPosition, boolean showRowNum,Map<String,Integer> cellWidth) {
		Iterator<Map<String, Object>> ir = collection.iterator();
		Map<String, Object> map = null;
		int i = 0;
		CellStyle cStyle = null;
		Point p = new Point(0, 0);
		
		p.x = startPosition.x;
		p.y = startPosition.y;
		
		while (ir.hasNext()) {
			i++;
			p.y = startPosition.y;
			
			if (showRowNum) {
				cStyle = (null!=cellStyles ? cellStyles[0] : null);
				ExcelService.writeCellSetCellWidth(wb, i, null, sheetIndex, p, cStyle, cellWidth.get("showRowNum"));
				p.y++;
			}
			
			map = ir.next();
			for (int k=0;k<columnOrder.length;k++) {
				System.out.println(columnOrder[k]);
				Object obj = map.get(columnOrder[k]);
				cStyle = (null!=cellStyles ? cellStyles[(showRowNum ? k+1 : k)] : null);
				ExcelService.writeCellSetCellWidth(wb, obj, null, sheetIndex, p, cStyle, cellWidth.get("column"+(k+1)));
				p.y++;
			}
			p.x++;
		}
	}
	
	//可指定单元格宽度，并且表格中有合并的单元格
	public static void collection2Xls2(HSSFWorkbook wb,
			Collection<Map<String, Object>> collection, String[] columnOrder, 
			CellStyle[] cellStyles, int sheetIndex,Point startPosition, boolean showRowNum,Map<String,Integer> cellWidth,Map<Integer,Integer> nodes) {
		Iterator<Map<String, Object>> ir = collection.iterator();
		Map<String, Object> map = null;
		int i = 0;
		CellStyle cStyle = null;
		Point p = new Point(0, 0);
		
		p.x = startPosition.x;
		p.y = startPosition.y;
		
		while (ir.hasNext()) {
			i++;
			p.y = startPosition.y;
			
			if (showRowNum) {
				cStyle = (null!=cellStyles ? cellStyles[0] : null);
				ExcelService.writeCellSetCellWidth(wb, i, null, sheetIndex, p, cStyle, cellWidth.get("showRowNum"));
				p.y++;
			}
			
			map = ir.next();
			for (int k=0;k<columnOrder.length;k++) {
				System.out.println(columnOrder[k]);
				Object obj = map.get(columnOrder[k]);
				cStyle = (null!=cellStyles ? cellStyles[(showRowNum ? k+1 : k)] : null);
				ExcelService.writeCellSetCellWidth(wb, obj, null, sheetIndex, p, cStyle, cellWidth.get("column"+(k+1)));
				//System.out.println("~~~~~~~~~~~~"+cellWidth.get("column"+(k+1)));
				Set<Integer> key = nodes.keySet();
		        for (Iterator it = key.iterator(); it.hasNext();) {
		        	Integer node = (Integer) it.next();
		        	if(node == p.y){
		        		p.y += nodes.get(node);
		        	}else{
		        		p.y++;
		        	}
		        }
			}
			p.x++;
		}
	}
	
	//按模板中每列的固定宽度导出，可指定sheet名字
	public static void collection2Xls2(HSSFWorkbook wb,
			Collection<Map<String, Object>> collection, String[] columnOrder, 
			CellStyle[] cellStyles, int sheetIndex,Point startPosition, boolean showRowNum,Map<String,Integer> cellWidth,String sheetName) {
		Iterator<Map<String, Object>> ir = collection.iterator();
		Map<String, Object> map = null;
		int i = 0;
		CellStyle cStyle = null;
		Point p = new Point(0, 0);
		
		p.x = startPosition.x;
		p.y = startPosition.y;
		
		while (ir.hasNext()) {
			i++;
			p.y = startPosition.y;
			
			if (showRowNum) {
				cStyle = (null!=cellStyles ? cellStyles[0] : null);
				ExcelService.writeCellSetCellWidth(wb, i, null, sheetIndex, p, cStyle, cellWidth.get("showRowNum"),sheetName);
				p.y++;
			}
			
			map = ir.next();
			for (int k=0;k<columnOrder.length;k++) {
				System.out.println(columnOrder[k]);
				Object obj = map.get(columnOrder[k]);
				cStyle = (null!=cellStyles ? cellStyles[(showRowNum ? k+1 : k)] : null);
				ExcelService.writeCellSetCellWidth(wb, obj, null, sheetIndex, p, cStyle, cellWidth.get("column"+(k+1)),sheetName);
				p.y++;
			}
			p.x++;
		}
	}
	
	//指定单元格宽度和sheetName,且单元格中有合并
	public static void collection2Xls2(HSSFWorkbook wb,
			Collection<Map<String, Object>> collection, String[] columnOrder, 
			CellStyle[] cellStyles, int sheetIndex,Point startPosition, boolean showRowNum,Map<String,Integer> cellWidth,String sheetName,Map<Integer,Integer> nodes) {
		Iterator<Map<String, Object>> ir = collection.iterator();
		Map<String, Object> map = null;
		int i = 0;
		CellStyle cStyle = null;
		Point p = new Point(0, 0);
		
		p.x = startPosition.x;
		p.y = startPosition.y;
		
		while (ir.hasNext()) {
			i++;
			p.y = startPosition.y;
			
			if (showRowNum) {
				cStyle = (null!=cellStyles ? cellStyles[0] : null);
				ExcelService.writeCellSetCellWidth(wb, i, null, sheetIndex, p, cStyle, cellWidth.get("showRowNum"),sheetName);
				p.y++;
			}
			
			map = ir.next();
			for (int k=0;k<columnOrder.length;k++) {
				System.out.println(columnOrder[k]);
				Object obj = map.get(columnOrder[k]);
				cStyle = (null!=cellStyles ? cellStyles[(showRowNum ? k+1 : k)] : null);
				ExcelService.writeCellSetCellWidth(wb, obj, null, sheetIndex, p, cStyle, cellWidth.get("column"+(k+1)),sheetName);
				Set<Integer> key = nodes.keySet();
		        for (Iterator it = key.iterator(); it.hasNext();) {
		        	Integer node = (Integer) it.next();
		        	if(node == p.y){
		        		p.y += nodes.get(node);
		        	}else{
		        		p.y++;
		        	}
		        }
			}
			p.x++;
		}
	}
	
	
	/**
	 * 将文档对象保存到文件中。
	 * 
	 * @param HSSFWorkbook
	 *            wb Excel文档对象
	 * @param File
	 *            file 文件
	 * @return File写入成功的文件引用，失败返回null，可用于检测写入是否成功
	 * */
	public static File toXlsFile(HSSFWorkbook wb, File file) {
		try {
			file.createNewFile();
			FileOutputStream fileOut = new FileOutputStream(file, false);//
			wb.write(fileOut);// 把Workbook对象输出到文件workbook.xls中
			fileOut.close();
		} catch (FileNotFoundException e) {
			// System.err.println("文件未找到!");
			// e.printStackTrace();
			return null;
		} catch (IOException e) {
			// e.printStackTrace();
			return null;
		}
		return file;
	}

	/**
	 * 将文档对象保存到临时文件中。
	 * 
	 * @param HSSFWorkbook
	 *            wb Excel文档对象
	 * @return File写入成功的文件引用，失败返回null，可用于检测写入是否成功
	 * */
	public static File toTempXlsFile(HSSFWorkbook wb) {
		File file = null;
		try {
			file = File.createTempFile("ExcleExport", ".xls");
			file.deleteOnExit();
			FileOutputStream fileOut = new FileOutputStream(file, false);//
			wb.write(fileOut);// 把Workbook对象输出到文件workbook.xls中
			fileOut.close();
			file.deleteOnExit();
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return file;
	}

	/**
	 * 将文件加载到文档对象中并返回。
	 * 
	 * @param File
	 *            file 要加载的文件对象
	 * @return HSSFWorkbook XLS的文档对象
	 * 
	 * 可以用于导出时指定模板
	 * 也可用于导入时指定数据文件
	 * */
	public static HSSFWorkbook loadXlsToWb(File file) {
		HSSFWorkbook wb = null;
		try {
			InputStream input = new FileInputStream(file);
			if (file.exists() && file.canRead()) {
				POIFSFileSystem fs = new POIFSFileSystem(input);
				wb = new HSSFWorkbook(fs);
			}
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return wb;
	}

	public static void merginAndWirteCell(HSSFWorkbook wb, String titleString,
			Object object, Point startPoint, Point endPoint, Object object2,
			boolean b) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 导出一个完整的文件
	 */
	@RemoteMethod
	public static FileTransfer exportToXls(String fileName, String[] header, String[] properties, IExcelDataFormater objFormater, List<Map<String, Object>> content){
		HSSFWorkbook wb = new HSSFWorkbook();
		
		/*excel内容单元格格式*/
		CellStyle cStyle=wb.createCellStyle();
		cStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cStyle.setBorderLeft((short)1);
		cStyle.setBorderRight((short)1);
		cStyle.setBorderTop((short)1);
		cStyle.setBorderBottom((short)1);
		// 设置单元格字体
		org.apache.poi.ss.usermodel.Font contentFont = wb.createFont(); // 字体
		contentFont.setFontName("宋体");
		contentFont.setFontHeightInPoints((short)10);
		cStyle.setFont(contentFont);
		
		//写入表头
		writeObjectAryOnCells(wb, header, objFormater, 0, new Point(0,0), cStyle, true);
		
		//写入内容
		collectionToXls(wb, content, properties, cStyle, null, 0, new Point(1,0), false, null);
		File file = ExcelService.toTempXlsFile(wb);
		return FileUtil.getFileTransfer(fileName+".xls", file, "application/vnd.ms-excel");
	} 
	
}
