package com.cqut.util.combine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

/**
 * 
 * 操作Excel的公用方法 
 * @author lulin
 * 
 */
public abstract class SameMothed {
	/* 合并的最终文档路劲  */
	protected String name = null; 
	protected Workbook wb = null, wb1, wb2;
	/* 合并附件的主要方法  */
	public abstract boolean Together(Object obj1, Object obj2, Object obj3);
	
	/**
	 * 初始化WorkbookFactory
	 * @param obj1 需要合并的第一个文档 
	 * @param obj2 需要合并的第二个文档
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 */
	protected void first(Object obj1, Object obj2) throws Exception{
		InputStream inp1 = null;
		InputStream inp2 = null;
		inp1 = new FileInputStream("" + (String) obj1);
		inp2 = new FileInputStream("" + (String) obj2);
		wb1 = WorkbookFactory.create(inp1);
		wb2 = WorkbookFactory.create(inp2);
	}
	
	/**
	 * 
	 * @param _wb 需要保存的Workbook对象
	 * @param fileName 保存的目标对象路劲
	 * @return 保存后的Workbook对象
	 */
	protected Object Save(Workbook _wb, String fileName){
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("" + fileName);
			_wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return _wb;
	}
	
	/**
	 * 
	 * @param _wb1 要复制的Workbook对象
	 * @param fileName 
	 * @return 需要复制的Workbook目标对象
	 */
	protected Workbook Copy(Workbook _wb1,String filename){
		Save(_wb1, filename);
		InputStream inp = null;
		Workbook _wb = null;
		try {
			//根据目标文件的输出流，创建一个Workbook对象
			inp = new FileInputStream("" + filename);
			_wb = WorkbookFactory.create(inp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return _wb;
	}
	
	protected void addRow(Row _row2) {
		XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
	    style.setFillForegroundColor(HSSFColor.LIME.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		Sheet sheet=wb.getSheetAt(0);
		sheet.createRow(sheet.getLastRowNum()+1);
		Row row=sheet.getRow(sheet.getLastRowNum());
		Cell cell,cell2;
		for(short j=0;j<_row2.getLastCellNum();j++) {
			cell2=_row2.getCell(j);
			cell=row.getCell(j);
			if(cell==null)
				cell=row.createCell(j);
			cell.setCellStyle(style);
    		cell.setCellValue(cell2.toString());
    	}
	}
}
