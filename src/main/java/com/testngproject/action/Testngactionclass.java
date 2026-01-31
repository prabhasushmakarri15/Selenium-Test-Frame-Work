package com.testngproject.action;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testngproject.base.TestngpracticeBase;
import com.testngproject.utility.ExtentManager;
import com.testngproject.utility.LoogerManager;

public class Testngactionclass {
	private  WebDriver driver;
	private  WebDriverWait wait;
	private static Logger logger = LoogerManager.getlogger(Testngactionclass.class);

	public Testngactionclass(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		logger.info("Webdriver instace is created in action class");
	}
	//public String gettitle() {
		//return driver.getTitle();
	//}
  
	public void entertext(By by, String text) {
		try {
		waitforelemttobevisible(by);
		WebElement ele =driver.findElement(by);
		ele.clear();
		ele.sendKeys(text);
		ExtentManager.logstep("Entered text is: "+text);
		logger.info("entered text is "+text);
		}
		catch(Exception e) {
		     ExtentManager.logfailureswithscreenshot(TestngpracticeBase.getwebdriver(), "Unable to enter text", getelementdescription(by));
			logger.error("Unable to enter text",e.getMessage());
			
			
		}
	}
	public void click(By by) {
		String getelementdescriptionn=getelementdescription(by);
		try {
		
			waitforelemttobeclickable(by);
			System.out.println("click");
		WebElement ele =driver.findElement(by);
		ele.click();
		ExtentManager.logstep("clicked on button: "+ getelementdescriptionn);
		//logger.info("Element clicked is" + getelementdescription(by));
		logger.info("element clicked is " +getelementdescriptionn);
		}
		catch(Exception e) {
			ExtentManager.logfailureswithscreenshot(TestngpracticeBase.getwebdriver(), "Unable to click button",getelementdescriptionn);
			logger.info("Unable to click button",e.getMessage());
			
		}
	}
	public boolean isDisplayed(By by) {
		try {
			waitforelemttobevisible(by);
			
			logger.info("element is displayed " +getelementdescription(by));
			return driver.findElement(by).isDisplayed();

		} catch (Exception e) {
			
			logger.error("unable to get text:" + e.getMessage());
			return false;
		}

	}
	public String gettext(By by) {
		try {
			waitforelemttobevisible(by);
			return driver.findElement(by).getText();
		}
		catch(Exception e) {
			logger.error("error message not showing"+getelementdescription(by));
			
			return "error essage not displayed";
			
		}
		
	}


	private void waitforelemttobevisible(By ele) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		
		} catch (Exception e) {
			logger.error("element is not visible", e.getMessage());

		}

	}

	private void waitforelemttobeclickable(By ele) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		} catch (Exception e) {
			logger.error("element is not clickable", e.getMessage());

		}

	}

	private String getelementdescription(By ele) {
		if (driver == null) {
			return "Driver is not initialized.";
		}
		if (ele == null) {
			return "Locator is null.";
		}
		try {
			WebElement element = driver.findElement(ele);
			String name = element.getDomProperty("name");
			String id = element.getDomProperty("id");
			String text = element.getText();
			String className = element.getDomProperty("class");
			String placeholder = element.getDomProperty("placeholder");
			if (name != null && !name.isEmpty()) {
				return name;

			} else if (id != null && !id.isEmpty()) {
				return id;
			} else if (text != null && !text.isEmpty()) {
				return text;
			} else if (className != null && !className.isEmpty()) {
				return className;
			} else if (placeholder != null && !placeholder.isEmpty()) {
				return placeholder;
			} else
				return "";
		} catch (Exception e) {
			e.printStackTrace(); // Replace with a logger in a real-world scenario
			return "Unable to describe element due to error: " + e.getMessage();
		}
		

	}
	public boolean comparevalues(By by,String expectedstring) {
		try {
		waitforelemttobevisible(by);
		String value=driver.findElement(by).getText();
		if(value.equals(expectedstring)) {
			logger.info("Texts matched:", value+"matched with expected text"+expectedstring);
			return true;
		}
		else
		{
			logger.info("Texts not matched:", value+"not matched with expected text"+expectedstring);
			ExtentManager.logfailureswithscreenshot(TestngpracticeBase.getwebdriver(), "Textsnotmatched", expectedstring);
			return false;
		}
			}
		catch(Exception e) {
			logger.error("element not found", e.getMessage());
			return false;
		}
		
		
		
	}

}
