package com.cqut.service.derive;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.Region;
import org.apache.poi.ss.util.RegionUtil;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.io.FileTransfer;
import org.springframework.stereotype.Controller;

import com.cqut.dao.common.ICommonDao;
import com.cqut.service.derive.deriveInterface.IDeriveExcelService;
import com.cqut.service.util.fileManage.excel.ExcelService;
import com.cqut.util.SystemUtil;
import com.cqut.util.fileUtil.FileUtil;

/**
 * 集合类对象转化为XLS文档对象
 * @author chenqing 
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

@Controller  
@RemoteProxy
public class DeriveExcelService implements IDeriveExcelService{
	@Resource(name = "systemUtil")
	private SystemUtil systemUtil;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;
	
	@RemoteMethod
	public FileTransfer deriveExcel(String templateName,List<Map<String,Object>> dataList,String [] columnOrder,int lineNumber,Boolean orderNumber,String excelName){
		//systemParameters获取模板的路径
		File template = new File(systemUtil.getTemplatePath(templateName));
		HSSFWorkbook wb = ExcelService.loadXlsToWb(template);
		CellStyle[] styles = new CellStyle[columnOrder.length+1];
		//设置表格的样式
		for(int k=0;k<columnOrder.length+1;k++){
			styles[k] = wb.createCellStyle();
			styles[k].setAlignment(HSSFCellStyle.ALIGN_CENTER);
			styles[k].setBorderLeft((short)1);
			styles[k].setBorderRight((short)1);
			styles[k].setBorderTop((short)1);
			styles[k].setBorderBottom((short)1);
		}
        ExcelService.collection2Xls(wb, dataList, columnOrder, styles,0, new Point(lineNumber, 0),orderNumber,true);
        copyEnd1(wb,0,new Point(dataList.size()+3,0));
		File resultfile =  ExcelService.toTempXlsFile(wb);
		return FileUtil.getFileTransfer(excelName + ".xls", resultfile, "application/vnd.ms-excel");
	}
	
	
	//
	
	/** 
	* @Title: deriveExcel   ytf
	* @Description: TODO(导出) 
	* @param @param workName 工作表名字
	* @param @param dataList 数据源
	* @param @param columnOrder key 值数组
	* @param @param chinaeseName 第一列的中文名字
	* @param @param width  宽度
	* @param @param excelName  文件名
	* @param @return    描述 
	* @return FileTransfer    返回类型 
	*/ 
	@RemoteMethod
	public FileTransfer deriveExcel(String workName,List<Map<String,Object>> dataList,String [] columnOrder,String[] chinaeseName ,int[] width ,String excelName){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(workName);
		if (width.length==0) {
			for (int i = 0; i < columnOrder.length; i++) {
				sheet.setColumnWidth(i, width[i]*100); 
			}
		}else {
			for (int i = 0; i < width.length; i++) {
				sheet.setColumnWidth(i, width[i]*100); 
			}
		}
		
		HSSFRow row = sheet.createRow((int) 0);
		
		HSSFCell  cell= null;
        for (int i = 0; i < chinaeseName.length; i++) {
        	HSSFCellStyle headstyle = wb.createCellStyle();
            HSSFFont headerFont = (HSSFFont) wb.createFont(); //创建字体样式  
            headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
            headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
             //headerFont.setFontName("Times New Roman");  //设置字体类型  
             //headerFont.setFontHeightInPoints((short) 8);    //设置字体大小  
            headstyle.setFont(headerFont);
        	cell= row.createCell(i);
        	cell.setCellStyle(headstyle);
        	cell.setCellValue(chinaeseName[i]);
        	
		}
        
        HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        for (int i = 0; i < dataList.size(); i++) {
        	row = sheet.createRow((int)i+1);
        	for (int j = 0; j < columnOrder.length; j++) {
        		cell = row.createCell(j);
        		cell.setCellStyle(style);
        		cell.setCellValue(dataList.get(i).get(columnOrder[j])+"");
			}
		}
       File resultfile =  ExcelService.toTempXlsFile(wb);
	   return FileUtil.getFileTransfer(excelName + ".xls", resultfile, "application/vnd.ms-excel");
	}
	/** 
	* @Title: deriveExcel   ytf
	* @Description: TODO(导出) 
    * @param @param title 标题
	* @param @param workName 工作表名字
	* @param @param dataList 数据源
	* @param @param columnOrder key 值数组
	* @param @param chinaeseName 第一列的中文名字
	* @param @param width  宽度
	* @param @param algin  对齐方式
	* @param @param excelName  文件名
	* @param @return    描述 
	* @return FileTransfer    返回类型 
	*/ 
	@RemoteMethod
	public FileTransfer deriveExcelT(String title,String workName,List<Map<String,Object>> dataList,String [] columnOrder,String[] chinaeseName,String[] algin ,int[] width ,String excelName){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(workName);
		if (width.length==0) {
			for (int i = 0; i < columnOrder.length; i++) {
				sheet.setColumnWidth(i, width[i]*100); 
			}
		}else {
			for (int i = 0; i < width.length; i++) {
				sheet.setColumnWidth(i, width[i]*100); 
			}
		}
		HSSFCell  cell= null;
		HSSFRow row = sheet.createRow((int) 0);
		// 单元格合并   
        // 四个参数分别是：起始行，起始列，结束行，结束列   
        sheet.addMergedRegion(new Region(0, (short) (0), 0,  (short) (columnOrder.length-1)));   
        cell = row.createCell(0);
        cell.setCellValue(title); // 跨单元格显示的数据  
        HSSFFont headerFont = (HSSFFont) wb.createFont();	//创建字体样式
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
        headerFont.setFontHeightInPoints((short) 16);	//设置字体大小
        
        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setFont(headerFont);
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
       
        cell.setCellStyle(style1); // 样式   
		
		row = sheet.createRow((int) 1);
		
        for (int i = 0; i < chinaeseName.length; i++) {
        	HSSFCellStyle headstyle = wb.createCellStyle();
            HSSFFont headFont = (HSSFFont) wb.createFont(); //创建字体样式  
            headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
            headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
             //headFont.setFontName("Times New Roman");  //设置字体类型  
            headFont.setFontHeightInPoints((short) 10);    //设置字体大小  
            headstyle.setFont(headFont);
        	cell= row.createCell(i);
        	cell.setCellStyle(headstyle);
        	cell.setCellValue(chinaeseName[i]);
		}
        
       
        for (int i = 0; i < dataList.size(); i++) {
        	row = sheet.createRow((int)i+2);
        	for (int j = 0; j < columnOrder.length; j++) {
        		 HSSFCellStyle style = wb.createCellStyle();
        		style.setAlignment(setAligin(algin[j]));
        		cell = row.createCell(j);
        		cell.setCellStyle(style);
        		cell.setCellValue(dataList.get(i).get(columnOrder[j])+"");
			}
		}
       File resultfile =  ExcelService.toTempXlsFile(wb);
	   return FileUtil.getFileTransfer(excelName + ".xls", resultfile, "application/vnd.ms-excel");
	}
	private short setAligin(String algin) {
		if (algin.equals("右")) {
			return HSSFCellStyle.ALIGN_RIGHT;
		}else if (algin.equals("中")) {
			return HSSFCellStyle.ALIGN_CENTER;
		}else {
			return HSSFCellStyle.ALIGN_LEFT;
		}
	}
	
	/** 
	* @Title: deriveExcel   lfx
	* @Description: TODO(导出) 
    * @param @param title 标题
	* @param @param workName 工作表名字
	* @param @param dataList 数据源
	* @param @param columnOrder key 值数组
	* @param @param chinaeseName 第一列的中文名字
	* @param @param width  宽度
	* @param @param algin  对齐方式
	* @param @param excelName  文件名
	* @param @return    描述 
	* @return FileTransfer    返回类型 
	*/ 
	@RemoteMethod
	public FileTransfer deriveExcelL(String title,String workName,List<Map<String,Object>> dataList,String [] columnOrder,String[] chinaeseName,String[] algin ,int[] width ,String excelName){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(workName);
		if (width.length==0) {
			for (int i = 0; i < columnOrder.length; i++) {
				sheet.setColumnWidth(i, width[i]*100); 
			}
		}else {
			for (int i = 0; i < width.length; i++) {
				sheet.setColumnWidth(i, width[i]*100); 
			}
		}
		HSSFCell  cell= null;
		HSSFRow row = sheet.createRow((int) 0);
		// 单元格合并   
        // 四个参数分别是：起始行，起始列，结束行，结束列   
        sheet.addMergedRegion(new Region(0, (short) (0), 0,  (short) (2)));   
        cell = row.createCell(0);
        cell.setCellValue(title); // 跨单元格显示的数据  
        HSSFFont headerFont = (HSSFFont) wb.createFont();	//创建字体样式
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
        headerFont.setFontHeightInPoints((short) 12);	//设置字体大小
        
        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setFont(headerFont);
        style1.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式
       
        cell.setCellStyle(style1); // 样式   
		
		//row = sheet.createRow((int) 1);
        int rows=1;
        HSSFRow orow = sheet.createRow((int) 1);
       
        for (int i = 0; i < dataList.size(); i++) {
        	for (int j = 0; j < chinaeseName.length; j++) {
            	HSSFCellStyle headstyle = wb.createCellStyle();
                HSSFFont headFont = (HSSFFont) wb.createFont(); //创建字体样式  
                headstyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式
                headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
                headFont.setFontHeightInPoints((short) 10);    //设置字体大小  
                headstyle.setFont(headFont);
            	cell= orow.createCell(0);
            	cell.setCellStyle(headstyle);
            	cell.setCellValue(chinaeseName[j]);
            	
    			HSSFCellStyle style = wb.createCellStyle();
        		style.setAlignment(setAligins(algin[j]));
        		cell = orow.createCell(1);
        		cell.setCellStyle(style);
        		cell.setCellValue(dataList.get(i).get(columnOrder[j])+"");
        		rows = rows + 1;
    			orow = sheet.createRow((int) rows);
    		}
		}
       File resultfile =  ExcelService.toTempXlsFile(wb);
	   return FileUtil.getFileTransfer(excelName + ".xls", resultfile, "application/vnd.ms-excel");
	}
	private short setAligins(String algin) {
		if (algin.equals("右")) {
			return HSSFCellStyle.ALIGN_RIGHT;
		}else if (algin.equals("中")) {
			return HSSFCellStyle.ALIGN_CENTER;
		}else {
			return HSSFCellStyle.ALIGN_LEFT;
		}
	}

	/** 
	* @Title: deriveExcelSpecial   lfx
	* @Description: TODO(导出) 
	* @param @param workName 工作表名字
	* @param @param dataList 数据源
	* @param @param columnOrder key 值数组
	* @param @param chinaeseName 第一列的中文名字
	* @param @param width  宽度
	* @param @param excelName  文件名
	* @param @return    描述 
	* @return FileTransfer    返回类型 
	*/ 
	@RemoteMethod
	public FileTransfer deriveExcelSpecial(String workName,List<Map<String,Object>> dataList,String [] columnOrder,String[] chinaeseName ,int[] width ,String excelName){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(workName);
		if (width.length==0) {
			for (int i = 0; i < columnOrder.length; i++) {
				sheet.setColumnWidth(i, width[i]*100); 
			}
		}else {
			for (int i = 0; i < width.length; i++) {
				sheet.setColumnWidth(i, width[i]*100); 
			}
		}
		
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		HSSFFont font=wb.createFont();
		font.setFontHeightInPoints((short)50);
		style.setFont(font);
		HSSFCell  cell= null;
        for (int i = 0; i < chinaeseName.length; i++) {
        	cell= row.createCell(i);
        	cell.setCellStyle(style);
        	cell.setCellValue(chinaeseName[i]);
		}
        for (int i = 0; i < dataList.size(); i++) {
        	row = sheet.createRow((int)i+1);
        	for (int j = 0; j < columnOrder.length; j++) {
        		cell = row.createCell(j);
        		cell.setCellStyle(style);
        		cell.setCellValue(dataList.get(i).get(columnOrder[j])+"");
			}
		}
       File resultfile =  ExcelService.toTempXlsFile(wb);
	   return FileUtil.getFileTransfer(excelName + ".xls", resultfile, "application/vnd.ms-excel");
	}
	
	/** 
	* @Title: deriveExcelSpecial   lfx
	* @Description: TODO(导出) 
	* @param @param workName 工作表名字
	* @param @param dataList 数据源
	* @param @param columnOrder key 值数组
	* @param @param chinaeseName 第一列的中文名字
	* @param @param width  宽度
	* @param @param excelName  文件名
	* @param @return    描述 
	* @return FileTransfer    返回类型 
	*/ 
	@RemoteMethod
	public FileTransfer deriveExcelOfPaper(String workName,List<Map<String,Object>> dataList,String [] columnOrder,String[] chinaeseName ,int[] width ,String excelName){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(workName);
		if (width.length==0) {
			for (int i = 0; i < columnOrder.length; i++) {
				sheet.setColumnWidth(i, 100); 
			}
		}else {
			for (int i = 0; i < width.length; i++) {
				sheet.setColumnWidth(i, width[i]*100); 
			}
		}
		
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居左格式
		HSSFCell  cell= null;
        for (int i = 0; i < chinaeseName.length; i++) {
        	cell= row.createCell(i);
        	cell.setCellStyle(style);
        	cell.setCellValue(chinaeseName[i]);
		}
        for (int i = 0; i < dataList.size(); i++) {
        	row = sheet.createRow((int)i+1);
        	for (int j = 0; j < columnOrder.length; j++) {
        		cell = row.createCell(j);
        		cell.setCellStyle(style);
        		cell.setCellValue(dataList.get(i).get(columnOrder[j])+"");
			}
		}
       File resultfile =  ExcelService.toTempXlsFile(wb);
	   return FileUtil.getFileTransfer(excelName + ".xls", resultfile, "application/vnd.ms-excel");
	}
	
	/** 
	* @Title: deriveExcelPaper   lfx
	* @Description: TODO(导出统考周考试分卷汇总表) 
	* @param @param workName 工作表名字
	* @param @param dataList 数据源
	* @param @param columnOrder key 值数组
	* @param @param chinaeseName 第一列的中文名字
	* @param @param width  宽度
	* @param @param excelName  文件名
	* @param @return    描述 
	* @return FileTransfer    返回类型 
	*/ 
	@RemoteMethod
	public FileTransfer deriveExcelPaper(String workName,List<Map<String,Object>> dataList,String [] columnOrder,String[] chinaeseName ,int[] width ,String excelName){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(workName);
		if (width.length==0) {
			for (int i = 0; i < columnOrder.length; i++) {
				sheet.setColumnWidth(i, 100); 
			}
		}else {
			for (int i = 0; i < width.length; i++) {
				sheet.setColumnWidth(i, width[i]*100); 
			}
		}
		
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居左的格式
		HSSFCell  cell= null;
		String[] heads = new String[]{"试卷编号：","课程名称:","任课教师:","考试时间:","考试地点:","考试班级:"};
		int rows=0;
		for (int k = 0; k < dataList.size(); k++) {
			for (int i = 0; i < heads.length; i++) {
				cell= row.createCell(0);
	        	cell.setCellStyle(style);
				cell.setCellValue(heads[i]);
				cell= row.createCell(1);
				if (dataList.get(k).get(columnOrder[i])!=null) {
					cell.setCellValue(dataList.get(k).get(columnOrder[i])+"");
				}else {
					cell.setCellValue(" ");
				}
	    		cell.setCellStyle(style);
	    		if(heads[i] != "考试班级:"){
	    			rows = rows + 1;
	    		}
	    		else{
	    			rows = rows + 3;
	    		}
	    		rows = rows + 1;
				row = sheet.createRow((int) rows);
			}
		}
		
       File resultfile =  ExcelService.toTempXlsFile(wb);
	   return FileUtil.getFileTransfer(excelName + ".xls", resultfile, "application/vnd.ms-excel");
	}
	
	/** 
	* @Title: deriveExcelOfBKPaper   lfx
	* @Description: TODO(导出补考考试分卷汇总表) 
	* @param @param workName 工作表名字
	* @param @param dataList 数据源
	* @param @param columnOrder key 值数组
	* @param @param chinaeseName 第一列的中文名字
	* @param @param width  宽度
	* @param @param excelName  文件名
	* @param @return    描述 
	* @return FileTransfer    返回类型 
	*/ 
	@RemoteMethod
	public FileTransfer deriveExcelOfBKPaper(String workName,List<Map<String,Object>> dataList,String [] columnOrder,String[] chinaeseName ,int[] width ,String excelName){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(workName);
		if (width.length==0) {
			for (int i = 0; i < columnOrder.length; i++) {
				sheet.setColumnWidth(i, 100); 
			}
		}else {
			for (int i = 0; i < width.length; i++) {
				sheet.setColumnWidth(i, width[i]*100); 
			}
		}
		
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居左的格式
		HSSFCell  cell= null;
		String[] heads = new String[]{"课程名称:","监考教师:","考试时间:","考试地点:"};
		int rows=0;
		for (int k = 0; k < dataList.size(); k++) {
			for (int i = 0; i < heads.length; i++) {
				cell= row.createCell(0);
	        	cell.setCellStyle(style);
				cell.setCellValue(heads[i]);
				cell= row.createCell(1);
				if (dataList.get(k).get(columnOrder[i])!=null) {
					cell.setCellValue(dataList.get(k).get(columnOrder[i])+"");
				}else {
					cell.setCellValue(" ");
				}
	    		cell.setCellStyle(style);
	    		if(heads[i] != "考试地点:"){
	    			rows = rows + 1;
	    		}
	    		else{
	    			rows = rows + 3;
	    		}
	    		rows = rows + 1;
				row = sheet.createRow((int) rows);
			}
		}
		
       File resultfile =  ExcelService.toTempXlsFile(wb);
	   return FileUtil.getFileTransfer(excelName + ".xls", resultfile, "application/vnd.ms-excel");
	}
	
	@RemoteMethod
	public FileTransfer deriveExcelQuick(String[] properties,List<Map<String,Object>> dataList,String[] columnNames,String excelName){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("book");
		for(int i=0;i<columnNames.length;i++){
			sheet.setColumnWidth(i, 8000);
		}
		
		//创建标题
		HSSFRow row = sheet.createRow((int) 0);
		sheet.addMergedRegion(new CellRangeAddress(
	            0, //first row (0-based)
	            0, //last row  (0-based)
	            0, //first column (0-based)
	            columnNames.length-1  //last column  (0-based)
	    ));
		Cell titleCell = row.createCell(0);
		titleCell.setCellValue(excelName);
		
		//设置标题样式
		HSSFCellStyle titleStyle = wb.createCellStyle();
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFFont titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short)20);
		titleStyle.setFont(titleFont);
		titleCell.setCellStyle(titleStyle);
		
		
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setBoldweight((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCell  cell= null;
		HSSFRow headRow = sheet.createRow((int) 1);
		for (int i = 0; i < columnNames.length; i++) {
			cell= headRow.createCell(i);
			cell.setCellValue(columnNames[i]);
			cell.setCellStyle(style);
		}
		for (int i = 0; i < dataList.size(); i++) {
			row = sheet.createRow((int)i+2);
			for (int j = 0; j < properties.length; j++) {
				cell = row.createCell(j);
				if(dataList.get(i).get(properties[j])==null){
					cell.setCellValue("");
				}else{
					cell.setCellValue(dataList.get(i).get(properties[j])+"");
				}
			}
		}
		File resultfile =  ExcelService.toTempXlsFile(wb);
		return FileUtil.getFileTransfer(excelName + ".xls", resultfile, "application/vnd.ms-excel");
	}
	
	//提供表格末尾签字的地方
	@RemoteMethod
	public FileTransfer deriveExcelNew(String workName,List<Map<String,Object>> dataList,String [] columnOrder,String[] chinaeseName ,int[] width ,String excelName){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(workName);
		if (width.length==0) {
			for (int i = 0; i < columnOrder.length; i++) {
				sheet.setColumnWidth(i, width[i]*100); 
			}
		}else {
			for (int i = 0; i < width.length; i++) {
				sheet.setColumnWidth(i, width[i]*100); 
			}
		}
		
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		HSSFCell  cell= null;
        for (int i = 0; i < chinaeseName.length; i++) {
        	cell= row.createCell(i);
        	cell.setCellStyle(style);
        	cell.setCellValue(chinaeseName[i]);
		}
        for (int i = 0; i < dataList.size(); i++) {
        	row = sheet.createRow((int)i+1);
        	for (int j = 0; j < columnOrder.length; j++) {
        		cell = row.createCell(j);
        		cell.setCellStyle(style);
        		cell.setCellValue(dataList.get(i).get(columnOrder[j])+"");
			}
		}
       copyEnd2(wb,0,new Point(dataList.size()+1,0));
       File resultfile =  ExcelService.toTempXlsFile(wb);
	   return FileUtil.getFileTransfer(excelName + ".xls", resultfile, "application/vnd.ms-excel");
	}
	
	//可以修改模板的标题行
	@RemoteMethod
	public FileTransfer deriveExcelChangeTitle(String templateName,List<Map<String,Object>> dataList,String [] columnOrder,int lineNumber,Boolean orderNumber,String excelName,String graduationProjectInfoName,String title,int copyEnd,double fontSize){
		File template = new File(systemUtil.getSystemParameter(templateName));
		HSSFWorkbook wb = ExcelService.loadXlsToWb(template);
		
		HSSFFont font=wb.createFont();
		font.setFontHeightInPoints((short)fontSize);
		
		CellStyle[] styles = new CellStyle[columnOrder.length+1];
		for(int k=0;k<columnOrder.length+1;k++){
			styles[k] = wb.createCellStyle();
			if(k==1){
				styles[k].setAlignment(HSSFCellStyle.ALIGN_LEFT);
			}else{
				styles[k].setAlignment(HSSFCellStyle.ALIGN_CENTER);
			}
			styles[k].setBorderLeft((short)1);
			styles[k].setBorderRight((short)1);
			styles[k].setBorderTop((short)1);
			styles[k].setBorderBottom((short)1);
			styles[k].setWrapText(true);
			styles[k].setFont(font);
		}
		graduationProjectInfoName = graduationProjectInfoName.substring(1, 5);
		Map<String,Integer> cellWidth = getCellWidth(templateName);
		ExcelService.writeCell(wb, "("+graduationProjectInfoName +")"+title, null, 0, new Point(0,0), null, false);
        ExcelService.collection2Xls2(wb, dataList, columnOrder, styles,0, new Point(lineNumber, 0),orderNumber,cellWidth);
		
        switch(copyEnd){
        	case 1:copyEnd1(wb,0,new Point(dataList.size()+3,0));break;
        	case 2:copyEnd2(wb,0,new Point(dataList.size()+3,0));break;
        }
        
		File resultfile =  ExcelService.toTempXlsFile(wb);
		return FileUtil.getFileTransfer(excelName + ".xls", resultfile, "application/vnd.ms-excel");
	}
	
	//表格最后签字部分
	@RemoteMethod
	public static void copyEnd1(HSSFWorkbook wb, int sheetIndex,Point startPosition) {
		HSSFSheet sheet = wb.getSheetAt(sheetIndex);
		
		//合并单元格
		CellRangeAddress region1 = new CellRangeAddress(startPosition.x,startPosition.x,1,3);
		sheet.addMergedRegion(region1);
		ExcelService.writeCellSetCellWidth(wb, "________________学院 ______________________专业 ____________________班", null, 0, new Point(startPosition.x,1), null, 0);
		
		CellRangeAddress region2 = new CellRangeAddress(startPosition.x+1,startPosition.x+1,1,3);
		sheet.addMergedRegion(region2);
		ExcelService.writeCellSetCellWidth(wb, "注：1、此表一式二份，在第7学期期末交教务处实践教学科备案，二级学院自存一份。", null, 0, new Point(startPosition.x+1,1), null, 0);
		ExcelService.writeCellSetCellWidth(wb, "   2、题目类型：应用研究或理论研究。", null, 0, new Point(startPosition.x+2,1), null, 0);
		ExcelService.writeCellSetCellWidth(wb, "   3、题目来源：分为自拟、教师科研和生产实际。", null, 0, new Point(startPosition.x+3,1), null, 0);
		
		CellRangeAddress region3 = new CellRangeAddress(startPosition.x+4,startPosition.x+4,1,3);
		sheet.addMergedRegion(region3);
		ExcelService.writeCellSetCellWidth(wb, "_____________学院（盖章）     二级学院院长___________（签字）    教研室主任:", null, 0, new Point(startPosition.x+4,1), null, 0);
		
		CellRangeAddress region4 = new CellRangeAddress(startPosition.x+5,startPosition.x+5,1,2);
		sheet.addMergedRegion(region4);
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		ExcelService.writeCellSetCellWidth(wb, "______年____月___日", null, 0, new Point(startPosition.x+5,1), cellStyle, 0);
	}
	
	//表格最后签字部分
	@RemoteMethod
	public static void copyEnd2(HSSFWorkbook wb, int sheetIndex,Point startPosition) {
		HSSFSheet sheet = wb.getSheetAt(sheetIndex);
		
		CellStyle cellStyle1 = wb.createCellStyle();
		cellStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		//合并单元格
		CellRangeAddress region1 = new CellRangeAddress(startPosition.x+1,startPosition.x+1,1,7);
		sheet.addMergedRegion(region1);
		ExcelService.writeCellSetCellWidth(wb, "注：此表一式二份，教务处实践教学科、二级学院各存一份。", null, 0, new Point(startPosition.x+1,1), cellStyle1, 0);
		
		CellRangeAddress region2 = new CellRangeAddress(startPosition.x+2,startPosition.x+2,1,7);
		sheet.addMergedRegion(region2);
		ExcelService.writeCellSetCellWidth(wb, "_____________学院（盖章）     二级学院院长___________（签字）", null, 0, new Point(startPosition.x+2,1), cellStyle1, 0);
																					         
		CellRangeAddress region3 = new CellRangeAddress(startPosition.x+4,startPosition.x+4,5,6);
		sheet.addMergedRegion(region3);
		CellStyle cellStyle2 = wb.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		ExcelService.writeCellSetCellWidth(wb, "_________年_____月____日", null, 0, new Point(startPosition.x+4,5), cellStyle2, 0);
		
		HSSFRow row1 = sheet.createRow(startPosition.x);
		HSSFRow row2 = sheet.createRow(startPosition.x+3);
		row1.setHeight((short)160);
		row2.setHeight((short)130);
	}
	
	//根据模板名得到单元格列宽
	@RemoteMethod
	private Map<String,Integer> getCellWidth(String templateName) {
		Map<String,Integer> cellWidth = new HashMap<String,Integer>();
		Properties prop = new Properties();
		InputStream inStream = DeriveExcelService.class.getClassLoader()
				.getResourceAsStream("excelCellWidth/"+templateName+".properties");
		try {
			prop.load(inStream);
			cellWidth.put("showRowNum", Integer.parseInt(prop.getProperty("showRowNum")));
			for(int i=1; i<prop.size(); i++){
				cellWidth.put("column"+i, Integer.parseInt(prop.getProperty("column"+i)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cellWidth;
	}
	
	//写入答辩分子情况表 学生部分
	@RemoteMethod
	public void writeGroupStuInfo(HSSFWorkbook wb, int sheet,List<Map<String,Object>> data,CellStyle[] styles,int startRow){
		CellStyle headStyle = wb.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont headFont = wb.createFont();
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short)12);
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headStyle.setFont(headFont);
		headStyle.setBorderLeft((short)1);
		headStyle.setBorderRight((short)1);
		headStyle.setBorderTop((short)1);
		headStyle.setBorderBottom((short)1);
		headStyle.setWrapText(true);
		String[] heads = new String[]{"序号","学生姓名","毕业设计（论文）题目","指导教师","职称","学历","备注"};
		for(int i=0; i<data.size()+1; i++){
			CellRangeAddress region = new CellRangeAddress(startRow+i,startRow+i,2,6);
			wb.getSheetAt(sheet).addMergedRegion(region);
			RegionUtil.setBorderBottom(1, region, wb.getSheetAt(sheet), wb);
			RegionUtil.setBorderLeft(1, region, wb.getSheetAt(sheet), wb);
			RegionUtil.setBorderRight(1, region, wb.getSheetAt(sheet), wb);
			RegionUtil.setBorderTop(1, region, wb.getSheetAt(sheet), wb);
		}
		for(int i=0; i<3; i++){
			ExcelService.writeCell(wb, heads[i], null, sheet, new Point(startRow,i), headStyle, false);
		}
		for(int i=3; i<7; i++){
			ExcelService.writeCell(wb, heads[i], null, sheet, new Point(startRow,i+4), headStyle, false);
		}
		
		for(int k=0; k<data.size(); k++){
			//答辩教师职称
			String fac_position = data.get(k).get("fac_position").toString();
			String sql = "SELECT codeTableName FROM codetable WHERE codeTableCode='"+fac_position+"'";
			List<Object> position = commonDao.executeAndReturn(sql);
			if(position.size() > 0){
				data.get(k).put("fac_position", position.get(0));
			}
		}
		
		//合并的单元格信息
		Map<Integer,Integer> nodes = new HashMap<Integer,Integer>();
		nodes.put(2, 5);
		
		Map<String,Integer> cellWidth = new HashMap<String,Integer>();
		for(int i=0; i<7; i++){
			if(i==5){
				cellWidth.put("column"+i, 3500);
			}else{
				cellWidth.put("column"+i, 2500);
			}
		}
		cellWidth.put("showRowNum", 2500);
		
		String [] columnOrder = new String[]{"stu_graduateName","pro_propositionName","fac_name","fac_position","fac_highestEducation","fac_majorNote"};
		ExcelService.collection2Xls2(wb,data,columnOrder,styles,sheet,new Point(startRow+1,0),true,cellWidth,nodes);
		
	}
	
	//写入答辩分子情况表 答辩教师部分
	@RemoteMethod
	public void writeGroupTeaInfo(HSSFWorkbook wb, int sheet,List<Map<String,Object>> data,CellStyle[] styles){
		CellStyle headStyle = wb.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont headFont = wb.createFont();
		headFont.setFontName("宋体");
		headFont.setFontHeightInPoints((short)12);
		headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headStyle.setFont(headFont);
		headStyle.setBorderLeft((short)1);
		headStyle.setBorderRight((short)1);
		headStyle.setBorderTop((short)1);
		headStyle.setBorderBottom((short)1);
		headStyle.setWrapText(true);
		String[] heads = new String[]{"教师姓名","性别","年龄","职称","学历","学科方向","备注"};
		
		Map<String,Integer> cellWidth = new HashMap<String,Integer>();
		for(int i=1; i<=7; i++){
			if(i==5){
				cellWidth.put("column"+i, 3500);
			}else{
				cellWidth.put("column"+i, 2500);
			}
		}
		
		for(int j=0; j<6; j++){
			ExcelService.writeCell(wb, heads[j], null, sheet, new Point(5,j+2), headStyle, false);
		}
		ExcelService.writeCell(wb, heads[6], null, sheet, new Point(5,9), headStyle, false);
		
		for(int i=0; i<data.size()+1; i++){
			CellRangeAddress region = new CellRangeAddress(5+i,5+i,7,8);
			wb.getSheetAt(sheet).addMergedRegion(region);
			RegionUtil.setBorderBottom(1, region, wb.getSheetAt(sheet), wb);
			RegionUtil.setBorderLeft(1, region, wb.getSheetAt(sheet), wb);
			RegionUtil.setBorderRight(1, region, wb.getSheetAt(sheet), wb);
			RegionUtil.setBorderTop(1, region, wb.getSheetAt(sheet), wb);
		}
		
		
		for(int k=0; k<data.size(); k++){
			//答辩教师职称
			String tea_position = data.get(k).get("tea_position").toString();
			String sql = "SELECT codeTableName FROM codetable WHERE codeTableCode='"+tea_position+"'";
			List<Object> position = commonDao.executeAndReturn(sql);
			if(position.size() > 0){
				data.get(k).put("tea_position", position.get(0));
			}
			
			//答辩教师性别
			String tea_sex = data.get(k).get("tea_sex").toString();
			if(tea_sex.equals("0")){
				data.get(k).put("tea_sex", "女");
			}else{
				data.get(k).put("tea_sex", "男");
			}
			
			//答辩教师年龄
			Calendar calendar = Calendar.getInstance();
			int curYear = calendar.get(Calendar.YEAR);
			String tea_dateOfBirth = data.get(k).get("tea_dateOfBirth").toString().substring(0, 4);
			Integer tea_year = curYear - Integer.parseInt(tea_dateOfBirth);
			data.get(k).put("tea_dateOfBirth", tea_year);
		}
		
		//合并的单元格信息
		Map<Integer,Integer> nodes = new HashMap<Integer,Integer>();
		nodes.put(7, 2);
		
		String [] columnOrder = new String[]{"tea_name","tea_sex","tea_dateOfBirth","tea_position","tea_highestEducation","tea_studyDirection","tea_majorNote"};
		ExcelService.collection2Xls2(wb, data, columnOrder, styles,sheet, new Point(6, 2),false,cellWidth,"第"+(sheet+1)+"答辩组",nodes);
	}
	
	//写入答辩分组情况表头部
	@RemoteMethod
	public void writeGroupTitle(HSSFWorkbook wb,int sheet,int schoolYear,String academyName,String professionName, String defensePlace, String defenseTime, String graduationProjectInfoId){
		for(int i=0; i<4; i++){
			CellRangeAddress region = new CellRangeAddress(i,i,1,10);
			wb.getSheetAt(sheet).addMergedRegion(region);
		}
		
		CellStyle style1 = wb.createCellStyle();
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font1 = wb.createFont();
		font1.setFontName("宋体");
		font1.setFontHeightInPoints((short)16);
		style1.setFont(font1);
		ExcelService.writeCell(wb, "重 庆 理 工 大 学", null, sheet, new Point(0,1), style1, false);
		
		CellStyle style2 = wb.createCellStyle();
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font2 = wb.createFont();
		font2.setFontName("宋体");
		font2.setFontHeightInPoints((short)16);
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style2.setFont(font2);
		ExcelService.writeCell(wb, "（"+(schoolYear+1)+"）届毕业答辩分组情况统计表（表四）", null, sheet, new Point(1,1), style2, false);
		
		Map<String,Integer> index = new LinkedHashMap<String,Integer>();
		String sql = "SELECT studentNum FROM graduate WHERE graduationProjectInfoId='"+graduationProjectInfoId+"'";
		List<Object> studentNums = commonDao.executeAndReturn(sql);
		if(studentNums.size() > 0){
			for(int i=0; i<studentNums.size(); i++){
				String studentNum = studentNums.get(i).toString();
				if(studentNum.length() == 11){
					String classNum = studentNum.substring(1, 3)+studentNum.charAt(4)+studentNum.charAt(6)+"-"+studentNum.charAt(8);
					int grade = Integer.parseInt(studentNum.substring(1, 3));
					if(grade == schoolYear%100 - 3){
						index.put(classNum, i);
					}
				}
			}
		}
		String classNo = studentNums.get(studentNums.size()-1).toString().substring(0, 8)+"1-";
		classNo += index.size();
		
		CellStyle style3 = wb.createCellStyle();
		style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font3 = wb.createFont();
		font3.setFontName("宋体");
		font3.setFontHeightInPoints((short)12);
		style3.setFont(font3);
		academyName = academyName.replace("学院", "");
		ExcelService.writeCell(wb, "（"+academyName+"）学院  （"+professionName+"）专业    （"+classNo+"）班  ", null, sheet, new Point(2,1), style3, false);
		
		CellStyle style4 = wb.createCellStyle();
		style4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font4 = wb.createFont();
		font4.setFontName("宋体");
		font4.setFontHeightInPoints((short)12);
		style4.setFont(font3);
		
		//格式化答辩时间
		String formatTime = "";
		if(!defenseTime.equals("") && defenseTime !=null){
			formatTime += defenseTime.substring(0, 4)+"年";
			String month = defenseTime.substring(5, 7);
			formatTime += Integer.parseInt(month)+"月";
			String day = defenseTime.substring(8, 10);
			formatTime += Integer.parseInt(day)+"日";
			String time = defenseTime.substring(11, 16);
			int hour = Integer.parseInt(time.substring(0,2));
			if(hour <= 12){
				formatTime += "上午";
				formatTime += hour+time.substring(2,5);
			}else{
				formatTime += "下午";
				formatTime += (hour-12)+time.substring(2,5);
			}
		}
		
		ExcelService.writeCell(wb, "第（"+(sheet+1)+"）答辩组   答辩地点：（"+defensePlace+"）    答辩日期：（"+formatTime+"）  ", null, sheet, new Point(3,1), style4, false);
	}
	
	//写入答辩分组情况表最后签字部分
	@RemoteMethod
	public void writeGroupSign(HSSFWorkbook wb,int sheet,int startRow){
		for(int i=0; i<3; i++){
			CellRangeAddress region = new CellRangeAddress(startRow+i,startRow+i,1,9);
			wb.getSheetAt(sheet).addMergedRegion(region);
		}
		
		CellStyle signStyle1 = wb.createCellStyle();
		signStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		HSSFFont signFont = wb.createFont();
		signFont.setFontName("宋体");
		signFont.setFontHeightInPoints((short)12);
		signStyle1.setFont(signFont);
		ExcelService.writeCell(wb, "注：1、此表在答辩日期前二周送交教务处实践教学科。", null, sheet, new Point(startRow,1), signStyle1, false);
		ExcelService.writeCell(wb, "    2、此表一式二份，教务处实践教学科，二级学院各一份。", null, sheet, new Point(startRow+1,1), signStyle1, false);
		
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DATE);
		CellStyle signStyle2 = wb.createCellStyle();
		signStyle2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		signStyle2.setFont(signFont);
		ExcelService.writeCell(wb, year+" 年 "+month+" 月 "+day+" 日", null, sheet, new Point(startRow+2,1), signStyle2, false);
	}
	
	//导出成绩汇总表
	@RemoteMethod
	public FileTransfer deriveExcelForGrade(String templateName,List<Map<String,Object>> dataList,String [] columnOrder,int lineNumber,Boolean orderNumber,String excelName,String graduationProjectInfoName,int schoolYear,String title,double fontSize){
		File template = new File(systemUtil.getSystemParameter(templateName));
		HSSFWorkbook wb = ExcelService.loadXlsToWb(template);
		
		HSSFFont font=wb.createFont();
		font.setFontHeightInPoints((short)fontSize);
		font.setFontName("宋体");
		
		CellStyle[] styles = new CellStyle[columnOrder.length+1];
		for(int k=0;k<columnOrder.length+1;k++){
			styles[k] = wb.createCellStyle();
			if(k==1){
				styles[k].setAlignment(HSSFCellStyle.ALIGN_LEFT);
			}else{
				styles[k].setAlignment(HSSFCellStyle.ALIGN_CENTER);
			}
			styles[k].setBorderLeft((short)1);
			styles[k].setBorderRight((short)1);
			styles[k].setBorderTop((short)1);
			styles[k].setBorderBottom((short)1);
			styles[k].setWrapText(true);
			styles[k].setFont(font);
		}
		
		Map<String,Integer> cellWidth = getCellWidth(templateName);
		
		
		//得到是多少届毕业设计
		//graduationProjectInfoName = graduationProjectInfoName.substring(1, 5);
		graduationProjectInfoName = schoolYear+1+"";;
		int grade = (schoolYear-3)%100; // 该毕业设计是多少级的
		
		//Map<String,List<Map<String,Object>>> students = new HashMap<String,List<Map<String,Object>>>();
		String oneStuNum = dataList.get(0).get("gra_studentNum").toString();
		String sqll = "SELECT org.organizationName,org.organizationCode FROM organization "+
					"AS org,graduate AS gra WHERE gra.professionalId=org.organizationCode "+
					"AND gra.studentNum='"+oneStuNum+"'";
		List<Object> data1 = commonDao.executeAndReturn(sqll);
		Object[] list1 = (Object[]) data1.get(0);
		String professionName = list1[0].toString();
		String organizationCode = list1[1].toString();
		
		String sql2 = "SELECT SECOND.organizationName FROM "+
					"organization AS FIRST,organization AS SECOND WHERE "+
					"FIRST.parentCode=SECOND.organizationCode AND "+
					"FIRST.organizationCode='"+organizationCode+"'";
		List<Object> data2 = commonDao.executeAndReturn(sql2);
		String academyName = data2.get(0).toString();
		
		//获取每个班最后一名学生的下标（留级生放在一班的最前面）
		Map<String,Integer> index = new LinkedHashMap<String,Integer>();
		
		for(int i=0; i<dataList.size(); i++){
			String studentNum = dataList.get(i).get("gra_studentNum").toString();
			//得到学生的班级号。例如:1038-1
			if(studentNum.length()==11){
				String classNum = studentNum.substring(1, 3)+studentNum.charAt(4)+studentNum.charAt(6)+"-"+studentNum.charAt(8);
				if(Integer.parseInt(studentNum.substring(1, 3)) == grade){
					index.put(classNum, i);
				}
			}	
		}
		
		Set<String> classNums = index.keySet();
		Iterator<String> ir = classNums.iterator();
		
		int m=0;
		int sheet=0;
		while(ir.hasNext()){
			List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
			String classNum = ir.next();
			for(;m<=index.get(classNum);m++){
				data.add(dataList.get(m));
			}
			ExcelService.collection2Xls2(wb, data, columnOrder, styles,sheet, new Point(lineNumber, 0),orderNumber,cellWidth,classNum);
			writeTitle(wb,sheet,graduationProjectInfoName,title);
			writeDate(wb,sheet,data,academyName,professionName);
			writeHead(wb,sheet,cellWidth);
			writeSign(wb,sheet,data);
			
			sheet++;
		}

		File resultfile =  ExcelService.toTempXlsFile(wb);
		return FileUtil.getFileTransfer(excelName + ".xls", resultfile, "application/vnd.ms-excel");
	}
	
	/*写入成绩汇总表标题部分*/
	@RemoteMethod
	public void writeTitle(HSSFWorkbook wb,int sheet,String graduationProjectInfoName,String title){
		CellStyle titleStyle = wb.createCellStyle();
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont titleFont = wb.createFont();
		titleFont.setFontName("宋体");
		titleFont.setFontHeightInPoints((short)20);
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleStyle.setFont(titleFont);
		CellRangeAddress region1 = new CellRangeAddress(0,0,0,13);
		wb.getSheetAt(sheet).addMergedRegion(region1);
		ExcelService.writeCell(wb, "("+graduationProjectInfoName +")"+title, null, sheet, new Point(0,0), titleStyle, false);
	}
	
	/*写入成绩汇总表日期部分*/
	@RemoteMethod
	public void writeDate(HSSFWorkbook wb,int sheet,List<Map<String,Object>> data,String academyName,String professionName){
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int date = calendar.get(Calendar.DATE);
		CellStyle dateStyle = wb.createCellStyle();
		HSSFFont dateFont = wb.createFont();
		dateFont.setFontName("宋体");
		dateFont.setFontHeightInPoints((short)12);
		dateFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		dateStyle.setFont(dateFont);
		CellRangeAddress region2 = new CellRangeAddress(2,2,0,13);
		wb.getSheetAt(sheet).addMergedRegion(region2);
		String classNo = data.get(data.size()-1).get("gra_studentNum").toString().substring(0, 9);
		academyName = academyName.replace("学院", "");
		ExcelService.writeCell(wb, "（"+academyName+"）学院 　（"+professionName+"）专 业　　（"+classNo+"）班 级　                                  （"+year+"）年（"+month+"）月（"+date+"）日", null, sheet, new Point(2,0), dateStyle, false);
		
		HSSFRow row1 = wb.getSheetAt(sheet).createRow(3);
		row1.setHeight((short)205);
	}
	
	//写入成绩汇总表表头部分
	@RemoteMethod
	public void writeHead(HSSFWorkbook wb,int sheet,Map<String,Integer> cellWidth){
		HSSFRow row = wb.getSheetAt(sheet).createRow(4);
		row.setHeight((short)490);
		HSSFFont font=wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short)10);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		CellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setBorderLeft((short)1);
		style.setBorderRight((short)1);
		style.setBorderTop((short)1);
		style.setBorderBottom((short)1);
		style.setWrapText(true);
		style.setFont(font);
		String[] heads = new String[]{"序 号","学号","学生姓名","题        目","指导 教师","职 称","成 绩","评阅 教师","职 称","成 绩","主答辩 教师","职 称","成 绩","总成绩"};
		for(int i=0; i< heads.length; i++){
			if(i==0){
				ExcelService.writeCellSetCellWidth(wb, heads[0], null, sheet, new Point(4,0), style, cellWidth.get("showRowNum"));
			}else {
				ExcelService.writeCellSetCellWidth(wb, heads[i], null, sheet, new Point(4,i), style, cellWidth.get("column"+i));
			}	
		}
	}
	
	
	//写入成绩汇总表最后签字部分
	@RemoteMethod
	public void writeSign(HSSFWorkbook wb,int sheet,List<Map<String,Object>> data){
		CellRangeAddress region1 = new CellRangeAddress(data.size()+6,data.size()+6,0,13);
		wb.getSheetAt(sheet).addMergedRegion(region1);
		CellStyle signStyle1 = wb.createCellStyle();
		signStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont signFont1 = wb.createFont();
		signFont1.setFontName("Times New Roman");
		signFont1.setFontHeightInPoints((short)11);
		signStyle1.setFont(signFont1);
		ExcelService.writeCell(wb, " 二级学院（公章）：_________________            二级学院院长（签字）：_________________            系(教研室)主任：", null, sheet, new Point(data.size()+6,0), signStyle1, false);
		
		CellStyle signStyle2 = wb.createCellStyle();
		signStyle2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		HSSFFont signFont2 = wb.createFont();
		signFont2.setFontName("黑体");
		signFont2.setFontHeightInPoints((short)11);
		signStyle2.setFont(signFont2);
		ExcelService.writeCell(wb, "注：1、外聘教师注上“*”号。", null, sheet, new Point(data.size()+8,2), signStyle2, false);
		ExcelService.writeCell(wb, "    2、此表一式二份，教务处实践教学科、二级学院各存一份。", null, sheet, new Point(data.size()+9,2), signStyle2, false);
	}
	
	@RemoteMethod
	public void test(List<Map<String,Object>> dataList){
		//System.out.println("popofasfasfasfdfafasfafasf");
	}

	@Override
	public FileTransfer deriveExcelForDefenseGroupTable(String templateName,
			List<List<List<Map<String, Object>>>> dataList,
			String[] columnOrder, int lineNumber, Boolean orderNumber,
			String excelName, String graduationProjectInfoId, int schoolYear) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public FileTransfer deriveExcelQuick(String[] columnName,
//			List<Map<String, Object>> dataList, String[] columnOrder,
//			int lineNumber, boolean orderNumber, String excelName) {
//		CellStyle[] styles = new CellStyle[columnOrder.length+1];
//		//创建excel
//		HSSFWorkbook wb = new HSSFWorkbook();
//		//创建工作簿
//		HSSFSheet sheet = wb.createSheet("book1");
//		//创建第一行
//		HSSFRow row  =sheet.createRow(0);
//		//设置表格的样式
////		for(int k=0;k<columnName.length+1;k++){
////			styles[k] = wb.createCellStyle();
////			styles[k].setAlignment(HSSFCellStyle.ALIGN_CENTER);
////			styles[k].setBorderLeft((short)1);
////			styles[k].setBorderRight((short)1);
////			styles[k].setBorderTop((short)1);
////			styles[k].setBorderBottom((short)1);
////		}
//		HSSFCell cell= null;
//		sheet.setColumnWidth(0, 100*256);
//		for(int i=0;i<columnName.length;i++){
//			cell= row.createCell(i);
//			cell.setCellValue(columnName[i]);
//			cell.setCellStyle(styles[i]);
//		}
//		HSSFCellStyle cellStyle=null;
//		cellStyle=wb.createCellStyle();
//        for (int i = 0; i < dataList.size(); i++) {
//        	row = sheet.createRow((int)i+1);
//        	for (int j = 0; j < columnOrder.length; j++) {
//        		cell = row.createCell(j);
//        		int length=dataList.get(i).get(columnOrder[j]).toString().getBytes().length;
//        		cell.setCellValue(dataList.get(i).get(columnOrder[j])+"");
//			}
//		}
//       File resultfile =  ExcelService.toTempXlsFile(wb);
//       return FileUtil.getFileTransfer(excelName + ".xls", resultfile, "application/vnd.ms-excel");
//
//	}	
	
	/** 
	* @Title: deriveExcelForSelect   lile
	* @Description: TODO有下拉框选择的(导出) 
    * @param @param title 标题
	* @param @param workName 工作表名字
	* @param @param dataList 数据源
	* @param @param columnOrder key 值数组
	* @param @param chinaeseName 第一列的中文名字
	* @param @param width  宽度
	* @param @param algin  对齐方式
	* @param @param excelName  文件名
	* @param @return    描述 
	* @return FileTransfer    返回类型 
	*/ 
	@RemoteMethod
	public FileTransfer deriveExcelForSelect(String title,String workName,List<Map<String,Object>> dataList,String [] columnOrder,String[] chinaeseName,String[] algin ,int[] width ,String excelName,String[] weekTimes,String[] examTimes,String[] isReport){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(workName);
		if (width.length==0) {
			for (int i = 0; i < columnOrder.length; i++) {
				sheet.setColumnWidth(i, width[i]*100); 
			}
		}else {
			for (int i = 0; i < width.length; i++) {
				sheet.setColumnWidth(i, width[i]*100); 
			}
		}
		HSSFCell  cell= null;
		HSSFRow row = sheet.createRow((int) 0);
		// 单元格合并   
        // 四个参数分别是：起始行，起始列，结束行，结束列   
        sheet.addMergedRegion(new Region(0, (short) (0), 0,  (short) (columnOrder.length-1)));   
        cell = row.createCell(0);
        cell.setCellValue(title); // 跨单元格显示的数据  
        HSSFFont headerFont = (HSSFFont) wb.createFont();	//创建字体样式
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
        headerFont.setFontHeightInPoints((short) 16);	//设置字体大小
        
        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setFont(headerFont);
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
       
        cell.setCellStyle(style1); // 样式   
		
		row = sheet.createRow((int) 1);
		
        for (int i = 0; i < chinaeseName.length; i++) {
        	HSSFCellStyle headstyle = wb.createCellStyle();
            HSSFFont headFont = (HSSFFont) wb.createFont(); //创建字体样式  
            headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
            headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗  
             //headFont.setFontName("Times New Roman");  //设置字体类型  
            headFont.setFontHeightInPoints((short) 10);    //设置字体大小  
            headstyle.setFont(headFont);
        	cell= row.createCell(i);
        	cell.setCellStyle(headstyle);
        	cell.setCellValue(chinaeseName[i]);
		}
        
       
        for (int i = 0; i < dataList.size(); i++) {
        	row = sheet.createRow((int)i+2);
        	for (int j = 0; j < columnOrder.length; j++) {
        		 HSSFCellStyle style = wb.createCellStyle();
        		style.setAlignment(setAligin(algin[j]));
        		cell = row.createCell(j);
        		cell.setCellStyle(style);
        		cell.setCellValue(dataList.get(i).get(columnOrder[j])+"");
			}
		}

        if(dataList.size()>0){
        	  //单元格设置成下拉框
            sheet= setHSSFValidation(sheet, examTimes, 2, dataList.size()+3, 7, 7);// 第6列的前dataList.size()行都设置为选择列表形式.
            sheet= setHSSFValidation(sheet, weekTimes, 2, dataList.size()+3, 8,8);
            sheet= setHSSFValidation(sheet, isReport, 2, dataList.size()+3, 9,9);
        }      
       File resultfile =  ExcelService.toTempXlsFile(wb);
	   return FileUtil.getFileTransfer(excelName + ".xls", resultfile, "application/vnd.ms-excel");
	}
	
	 /** 
     * 设置某些列的值只能输入预制的数据,显示下拉框. 
     * @Title: deriveExcelForSelect   lile
     * @param sheet 要设置的sheet. 
     * @param textlist 下拉框显示的内容 
     * @param firstRow 开始行 
     * @param endRow 结束行 
     * @param firstCol   开始列 
     * @param endCol  结束列 
     * @return 设置好的sheet. 
     */  
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet,  
            String[] textlist, int firstRow, int endRow, int firstCol,  
            int endCol) {  
        // 加载下拉列表内容  
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);  
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列  
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,endRow, firstCol, endCol);  
        // 数据有效性对象  
        HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);  
        sheet.addValidationData(data_validation_list);  
        return sheet;  
    }  
	
}
