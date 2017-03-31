package com.cqut.service.derive.deriveInterface;

import java.util.List;
import java.util.Map;

import org.directwebremoting.io.FileTransfer;



public interface IDeriveExcelService {
	public FileTransfer deriveExcel(String workName,List<Map<String,Object>> dataList,String [] columnOrder,String[] chinaeseName ,int[] width ,String excelName);
	public FileTransfer deriveExcelT(String title,String workName,List<Map<String,Object>> dataList,String [] columnOrder,String[] chinaeseName ,String[] algin ,int[] width ,String excelName);
	public FileTransfer deriveExcelSpecial(String workName,List<Map<String,Object>> dataList,String [] columnOrder,String[] chinaeseName ,int[] width ,String excelName);
	public FileTransfer deriveExcelPaper(String workName,List<Map<String,Object>> dataList,String [] columnOrder,String[] chinaeseName ,int[] width ,String excelName);
	public FileTransfer deriveExcel(String templateName,List<Map<String, Object>> dataList,String [] columnOrder,int lineNumber ,Boolean orderNumber,String excelName);
	public FileTransfer deriveExcelChangeTitle(String templateName,List<Map<String, Object>> dataList,String [] columnOrder,int lineNumber ,Boolean orderNumber,String excelName,String graduationProjectInfoName,String title,int copyEnd,double fontSize);
	public FileTransfer deriveExcelForGrade(String templateName,List<Map<String, Object>> dataList,String [] columnOrder,int lineNumber ,Boolean orderNumber,String excelName,String graduationProjectInfoName,int schoolYear,String title,double fontSize);
	public FileTransfer deriveExcelForDefenseGroupTable(String templateName,List<List<List<Map<String,Object>>>> dataList,String [] columnOrder,int lineNumber,Boolean orderNumber,String excelName,String graduationProjectInfoId,int schoolYear);
	public FileTransfer deriveExcelQuick(String[] properties,List<Map<String, Object>> dataList,String[] columnNames , String excelName);
	public FileTransfer deriveExcelNew(String excelName,List<Map<String, Object>> data, String[] properties,String[] chinesepro, int[] width, String excelName2);
	public FileTransfer deriveExcelOfBKPaper(String string,List<Map<String, Object>> data, String[] pro,String[] chineseStrings, int[] width, String excelName);
	public FileTransfer deriveExcelL(String excelName, String string,List<Map<String, Object>> count, String[] proper,String[] chineseStrings, String[] title, int[] width,String excelName2);
	public FileTransfer deriveExcelForSelect(String title,String workName,List<Map<String,Object>> dataList,String [] columnOrder,String[] chinaeseName,String[] algin ,int[] width ,String excelName,String[] weekTimes,String[] examTimes,String[] isReport);
}
