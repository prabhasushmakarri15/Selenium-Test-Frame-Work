package com.testngproject.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


import com.testngproject.action.Testngactionclass;
import com.testngproject.utility.ExtentManager;
import com.testngproject.utility.LoogerManager;

public class TestngpracticeBase {
	
	public static Properties prop;
	//private static WebDriver driver;
	//private static Testngactionclass actiondriver;
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	private static ThreadLocal<Testngactionclass> actiondriver=new ThreadLocal<>();
	private static ThreadLocal<PageObjectManager> pageManager = new ThreadLocal<>();
	public static final Logger logger=LoogerManager.getlogger(TestngpracticeBase.class);
	
	@BeforeSuite
	public void loadconfig() throws IOException {
		prop=new Properties();
		
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/config.properties");
		
		prop.load(fis);
		
		
	}
	@BeforeMethod
	public synchronized void setup() {
		long threadId = Thread.currentThread().getId();
	    System.out.println("DEBUG: Setup started for Thread " + threadId);
		configurebrowser();
		launchbrowser();
		WebDriver currentDriver = getwebdriver();
	    System.out.println("DEBUG: Thread " + threadId + " initialized Driver: " + currentDriver.hashCode());
	//	actiondriver=new Testngactionclass(driver);
		
		actiondriver.set(new Testngactionclass(getwebdriver()));
		pageManager.set(new PageObjectManager(getwebdriver()));
	}
	private synchronized void configurebrowser() {
		String browser=prop.getProperty("browser");
	
		if(browser.equals("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless"); // Run Chrome in headless mode
			options.addArguments("--disable-gpu"); // Disable GPU for headless mode
		//	options.addArguments("--window-size=1920,1080"); // Set window size
		//	options.addArguments("--start-maximized");
			options.addArguments("--disable-notifications"); // Disable browser notifications
			options.addArguments("--no-sandbox"); // Required for some CI environments like Jenkins
			options.addArguments("--disable-dev-shm-usage"); // Resolve issues in resource-limited environments
			//WebDriver.chromedriver().setup();
			driver.set(new ChromeDriver(options));
			ExtentManager.registerdriver(getwebdriver());
		}
		else if(browser.equals("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless"); // Run Firefox in headless mode
			options.addArguments("--disable-gpu"); // Disable GPU rendering (useful for headless mode)
			options.addArguments("--start-maximized");
			options.addArguments("--width=1920"); // Set browser width
			options.addArguments("--height=1080"); // Set browser height
			options.addArguments("--disable-notifications"); // Disable browser notifications
			options.addArguments("--no-sandbox"); // Needed for CI/CD environments
			options.addArguments("--disable-dev-shm-usage"); // Prevent crashes in low-resource environments

			driver.set(new FirefoxDriver(options));
			ExtentManager.registerdriver(getwebdriver());
		}
		else
			System.out.println("no browser");
		
		
		
		
		
	}
	private synchronized void launchbrowser() {
	//	getwebdriver().manage().window().maximize();
	//	getwebdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		getwebdriver().manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));
	    getwebdriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Increase slightly for headless
		getwebdriver().get(prop.getProperty("url"));
		
		
	}
	@AfterMethod
	public synchronized void quit() {
		
		if(getwebdriver()!=null) {
			try {
				getwebdriver().quit();
				driver.remove();
			}
			catch(Exception e) {
				logger.error("unable to quit the driver:" +e.getMessage());
			}
		}
		
		
		//actiondriver=null;
	//	driver=null;
		//driver.remove();
		actiondriver.remove();
		pageManager.remove();
		//ExtentManager.endtest();
		
	}
	public static WebDriver getwebdriver() {
		return driver.get();
	}
	
	public static Testngactionclass getactiondriver() {
		return actiondriver.get();
	}
	public static PageObjectManager getPage() {
        return pageManager.get();
    }
	
	
	

}
