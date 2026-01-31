package com.testngproject.utility;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> Test=new ThreadLocal<>();
	private static Map<Long,WebDriver> drivermap=new HashMap();
	
	
	public synchronized static ExtentReports getReport() {
		if(extent==null) {
		String path=System.getProperty("user.dir") +"/src/test/resources/extentreport/extentreport.html";
		//String path = System.getProperty("user.dir") + "/src/test/resources/extentreport/extentreport.html";
	
	
		ExtentSparkReporter spark=new ExtentSparkReporter(path);
		spark.config().setReportName("OrangeHRM");
		spark.config().setDocumentTitle("OrangeHRM document");
		spark.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("os name", "os.name");
		extent.setSystemInfo("Java Version", "java.version");
		extent.setSystemInfo("user name", "user.name");
		
		}
		return extent;
		
		
	}
	public synchronized static ExtentTest starttest(String name) {
		ExtentTest extenttest=getReport().createTest(name);
		Test.set(extenttest);
		return extenttest;
	}
	public synchronized static void endtest() {
		getReport().flush();
		extent.flush();
		Test.remove();
	}
	public synchronized static void logstep(String logmessage) {
		gettest().info(logmessage);
	}
	public synchronized static void logsucceswithscreenshot(WebDriver driver,String logmessage,String screenshotname) {
		gettest().pass(logmessage);
		screenshot(driver,screenshotname);
	}
	public synchronized static void logfailureswithscreenshot(WebDriver driver,String logmessage,String screenshotname){
		gettest().fail(logmessage);
		screenshot(driver,screenshotname);
	}
	private synchronized static void screenshot(WebDriver driver, String screenshotname){
		
		try {
			String screenShotBase64 = takescreenshot(driver,getTestName());
			gettest().info(screenshotname,com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String(screenShotBase64).build());
		} catch (Exception e) {
			gettest().fail("Failed to attach screenshot: "+screenshotname);
			e.printStackTrace();
		}
		
	}
	private synchronized static String takescreenshot(WebDriver driver, String testName) {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
		String destpath=System.getProperty("user.dir") + "/src/test/resources/screenshots/"+timestamp+"_"+testName+".png";
		               
		File finalpath=new File(destpath);
		try {
			FileUtils.copyFile(src, finalpath);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String Base64=convertToBase64(src);
		return Base64;
	}
	private synchronized static String convertToBase64(File src) {
		String base64Format="";
		//Read the file content into a byte array
		try {
			byte[] fileContent = FileUtils.readFileToByteArray(src);
			base64Format = Base64.getEncoder().encodeToString(fileContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return base64Format;
	}
	public synchronized static ExtentTest gettest() {
		return Test.get();
	}
	public synchronized static String getTestName() {
		
		return Test.get().getModel().getName();
	}
	public synchronized static void registerdriver(WebDriver driver) {
		drivermap.put(Thread.currentThread().getId(), driver);
	}

}
