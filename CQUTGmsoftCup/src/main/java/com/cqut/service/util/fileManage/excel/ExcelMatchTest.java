package com.cqut.service.util.fileManage.excel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cqut.util.common.StringUtil;

public class ExcelMatchTest {
	private String templatePath = "E:\\template\\scoreEnterOfTeaFac.xls";
	private String filePath = "E:\\template\\scoreEnterOfTeaEx.xls";
	private String [] columnOrder;
	private short[] needRow = new short[]{0,1,2,3,4,5,6};
	private int sheetIndex = 0;
	private int startLine = 3;
	
	public static void main(String[] args){
//		ExcelMatchTest test = new ExcelMatchTest();
//		System.out.println(test.matchTemplate());
	}
	
	public boolean matchAttachment(){
		HSSFWorkbook tp = ExcelService.loadXlsToWb(new File(templatePath));
		
		return false;
	}
	
	//匹配模板
	public boolean matchTemplate(){
		boolean result = false; 
		if(!StringUtil.isEmpty(templatePath)){
			HSSFWorkbook tp = ExcelService.loadXlsToWb(new File(templatePath));
			HSSFWorkbook fp = ExcelService.loadXlsToWb(new File(filePath));
			String tcontent1 = getTitleContent(tp, sheetIndex, startLine);
			String tcontent2 = getTitleContent(fp, sheetIndex, startLine);
			if(tcontent1.equals(tcontent2)){
				result = true;
			}
			System.out.println("1:"+tcontent1.equals(tcontent2));
			System.out.println("1:"+tcontent1);
			System.out.println("1:"+tcontent2);
		}
		return result;
	}
	
	//获取行数据
	public String getTitleContent(HSSFWorkbook tp, int sheetIndex, int startLine){
		HSSFSheet sheet = tp.getSheetAt(sheetIndex);
		HSSFRow row = sheet.getRow(startLine);
		String titleContent = "";
		int colNum = row.getPhysicalNumberOfCells();//获取数据的列数
		for(int i=0; i<colNum; i++){
			titleContent += getCellFormatValue(row.getCell((short)i))+"-";
		}
		return titleContent;
	}
	
	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getStringCellValue(HSSFCell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 获取单元格数据内容为日期类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getDateCellValue(HSSFCell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
						+ "-" + date.getDate();
			} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("日期格式不正确!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellFormatValue(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {

			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式

					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();

					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);
				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}
	
}
