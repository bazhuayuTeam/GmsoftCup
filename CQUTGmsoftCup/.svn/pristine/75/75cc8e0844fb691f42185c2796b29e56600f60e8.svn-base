package com.cqut.util.combine;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * 合并附件
 * 规则：相同格式的Excel
 * @author lulin
 */
@Component
@Controller
@RemoteProxy
public class GetTogether extends SameMothed {
	
	int[] ID;

	/**
	 * @param obj1 需要合并的第一个文档
	 * @param obj2 需要合并的第二个文档
	 * @param obj3 合并的最终文档
	 */
	@RemoteMethod
	public boolean Together(Object obj1, Object obj2, Object obj3) {
			name = (String) obj3; 
			try {
				first(obj1, obj2);
			} catch(Exception e){
				e.printStackTrace();
				return false;
			}
			wb = Copy(wb1, name);
			//选出Sheet(0) 
			Sheet sheet2 = wb2.getSheetAt(0);
			Sheet sheet = wb.getSheetAt(0);
			
			Row row, row2;
			//第一行(表头) 
			row2 = sheet2.getRow(0);
			row = sheet.getRow(0);
			
			Cell cell, cell2;
			
			ID = new int[row2.getLastCellNum() + row.getLastCellNum()];
			int k = -1;
			boolean b = false;
			//处理表头(第一行)
			for (int i = 0; row2.getCell(i) != null; i++) {
				int j = 0;
				b = false;
				cell2 = row2.getCell(i);
				for (j = 0; row.getCell(j) != null; j++) {
					cell = row.getCell(j);
					//判断单元格的值是否相同：对numeric,formula都throw Exception
					if (cell.getStringCellValue().equals(cell2.getStringCellValue())) {
						b = true;
						break;
					}
				}
				if (b) {
					ID[++k] = j;
				} else {
					ID[++k] = row.getLastCellNum();
					row.createCell(row.getLastCellNum());
					this.changeCellValue(row.getCell(row.getLastCellNum() - 1), cell2);
				}
			}
			//从第二行开始加数据
			for (short i = 1; i <= sheet2.getLastRowNum(); i++) {
				row2 = sheet2.getRow(i);
				addRow(ID, row2);
			}
			Save(wb, name);
		return true;
	}

	/**
	 * 添加一行数据
	 * @param id 记录数组
	 * @param _row2 行对象(从第二行开始)
	 */
	public void addRow(int[] id, Row _row2) {
		if (_row2 != null) {
			Font font = wb.createFont();
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);

			Sheet sheet = wb.getSheetAt(0);
			sheet.createRow(sheet.getLastRowNum() + 1);
			Row row = sheet.getRow(sheet.getLastRowNum());
			Cell cell, cell2;

			for (short j = 0; j < _row2.getLastCellNum(); j++) {
				cell2 = _row2.getCell(j);
				cell = row.getCell(id[j]);
				if (cell == null) {
					cell = row.createCell(id[j]);
				}
				if (cell2 != null) {
					this.changeCellValue(cell, cell2);
				}
			}
		}
	}

	public void changeCellValue(Cell cell1, Cell cell2) {
		if (cell1 != null && cell2 != null) {
			switch (cell2.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC: // 数值型
				cell1.setCellType(Cell.CELL_TYPE_NUMERIC);
				if (HSSFDateUtil.isCellDateFormatted(cell2)) {
					// 如果是Date类型则 ，获取该Cell的Date值
					cell1.setCellValue(HSSFDateUtil.getJavaDate(cell2
							.getNumericCellValue()));
				} else {// 纯数字，这里要判断是否为小数的情况，因为整数在写入时会被加上小数点
					String t = cell2.getNumericCellValue() + "";
					BigDecimal n = new BigDecimal(cell2.getNumericCellValue());
					// 判断是否有小数点
					if (t.indexOf(".") < 0) {
						cell1.setCellValue(n.intValue());
					} else {
						cell1.setCellValue(n.doubleValue());
					}
				}
				break;
			case Cell.CELL_TYPE_STRING: // 字符串型
				cell1.setCellType(Cell.CELL_TYPE_STRING);
				cell1.setCellValue(cell2.getRichStringCellValue().toString());
				break;
			case Cell.CELL_TYPE_FORMULA:// 公式型
				cell1.setCellType(Cell.CELL_TYPE_FORMULA);
				cell1.setCellFormula(cell2.getCellFormula());
				// 读公式计算值
				cell1.setCellValue(cell2.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_BOOLEAN:// 布尔
				cell1.setCellType(Cell.CELL_TYPE_BOOLEAN);
				cell1.setCellValue(cell2.getBooleanCellValue());
				break;
			/* 此行表示该单元格值为空 */
			case Cell.CELL_TYPE_BLANK: // 空值
				cell1.setCellType(Cell.CELL_TYPE_BLANK);
				cell1.setCellValue("");
				break;
			case Cell.CELL_TYPE_ERROR: // 故障
				cell1.setCellType(Cell.CELL_TYPE_ERROR);
				cell1.setCellValue("");
				break;
			default:
				cell1.setCellValue(cell2.getRichStringCellValue().toString());
			}
		}
	}

}
