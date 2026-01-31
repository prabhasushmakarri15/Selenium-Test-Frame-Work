package com.testngproject.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	

	public static List<String[]> getsheetdata(String path, String sheetname) {
		List<String[]> data=new ArrayList<>();
		try (FileInputStream fis= new FileInputStream(path);Workbook workbook= new XSSFWorkbook(fis)){
		Sheet sheet=workbook.getSheet(sheetname);
		if(sheet!=null) {
			for(Row row:sheet) {
				if(row.getRowNum()==0) {
					continue;
				}
			List<String> rowdata=new ArrayList<>();
			for(Cell cell:row) {
				rowdata.add(getCellValue(cell));
			}
			data.add(rowdata.toArray(new String[0]));
			}
			
			}
			
			} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return data;
	}

	private static String getCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}
		switch(cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			if(DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue().toString();
			}
			else
				return String.valueOf((int) cell.getNumericCellValue());
		case BOOLEAN:
			    return String.valueOf(cell.getBooleanCellValue());
		default:
			return "";
			
		
		
		
		}	
	}
	
	

}
