package com.wp.DTL645_Listener.export;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;


public class DataSource {
	/** 导出excel表 */
	@SuppressWarnings("rawtypes")
	public static void exportExcelSheet(HSSFWorkbook workbook,
			String sheetName, String[] titleList, List list) {
		/*
		 * HSSFFont font=workbook.createFont(); font.setFontName("Arial");
		 * font.setFontHeight((short) 10);
		 */
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// sheet.autoSizeColumn(0);
		HSSFRow row1 = sheet.createRow(0);
		
		// 创建单元格样式
	    HSSFCellStyle style = workbook.createCellStyle();
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	    //style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
	    style.setFillForegroundColor(HSSFColor.WHITE.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

	    // 设置边框
	    //style.setBottomBorderColor(HSSFColor.RED.index);
	    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	    style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		
		for (int i = 0; i < titleList.length; i++) {// 写入标题栏
			HSSFCell cell = row1.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(titleList[i]);
			

			switch(i){
			case 0:sheet.setColumnWidth(0, 2500);break;
			case 1:sheet.setColumnWidth(1, 2500);break;
			case 2:sheet.setColumnWidth(2, 2500);break;
			case 3:sheet.setColumnWidth(3, 2500);break;
			case 4:sheet.setColumnWidth(4, 2500);break;
			case 5:sheet.setColumnWidth(5, 2500);break;
			case 6:sheet.setColumnWidth(6, 2500);break;
			case 7:sheet.setColumnWidth(7, 2500);break;
			case 8:sheet.setColumnWidth(8, 2500);break;
			case 9:sheet.setColumnWidth(9, 2500);break;
			case 10:sheet.setColumnWidth(10, 2500);break;
			case 11:sheet.setColumnWidth(11, 2500);break;
			case 12:sheet.setColumnWidth(12, 2500);break;
			case 13:sheet.setColumnWidth(13, 2500);break;
			case 14:sheet.setColumnWidth(14, 2500);break;
			case 15:sheet.setColumnWidth(15, 2500);break;
			case 16:sheet.setColumnWidth(16, 2500);break;
			case 17:sheet.setColumnWidth(17, 2500);break;
			case 18:sheet.setColumnWidth(18, 2500);break;
			case 19:sheet.setColumnWidth(19, 2500);break;
			
			}
			cell.setCellStyle(style);
		}

		for (int j = 0; list != null && j < list.size(); j++) {// 写入内容
			Viewable item = (Viewable) list.get(j);
			HSSFRow rowContent = sheet.createRow(j + 1);
			String[] cellList = item.convert();
			for (int i = 0; i < cellList.length; i++) {
				HSSFCell cell = rowContent.createCell(i);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(cellList[i]);				
				cell.setCellStyle(style);
			}
			
		}
		
	}
}
