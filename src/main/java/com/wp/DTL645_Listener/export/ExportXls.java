package com.wp.DTL645_Listener.export;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;



public class ExportXls {

	/** 导出EXCEL表格 */
	@SuppressWarnings("rawtypes")
	public boolean exportExcel(String fileName, List list) {
		HSSFWorkbook workbook = new HSSFWorkbook();

		String titleList[] = { "绳1",
							   "绳2",
							   "绳3",
							   "绳4",
							   "倾角",
							   "罗盘"};
		DataSource.exportExcelSheet(workbook, "记录1", titleList, list);
		try {
			FileOutputStream fOut = new FileOutputStream(fileName);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		List list = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		for(int i = 0;i < 30000;i++){
			ComDataBean cb = new ComDataBean(i*100+"", i*100+1+"", i*100+2+"", i*100+3+"", i*100+4+"", i*100+5+"");
			list.add(cb);
		}
		new ExportXls().exportExcel("d:/"+sdf.format(new Date())+".xls", list);
	}
}
