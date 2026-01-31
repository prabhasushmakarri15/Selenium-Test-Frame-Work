package com.testngproject.utility;

import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
private static final String path= System.getProperty("user.dir")+"/src/test/resources/TestData/TestData.xlsx";


public static Object[][] getsheetdata(String sheetname){
	List<String[]> sheetdata=ExcelReader.getsheetdata(path,sheetname);
	Object[][] data =  new Object[sheetdata.size()][sheetdata.get(0).length];
	
	for(int i=0; i<sheetdata.size(); i++) {
		data[i] = sheetdata.get(i);
	}
	
	return data;
}

@DataProvider(name="valid login users")
public static Object[][] validlogin() {
	return getsheetdata("validuser");
	
	
}
@DataProvider(name="Invalid login users")
public static Object[][] invalidlogin() {
	return getsheetdata("Invaliduser");
	
	
}

}
