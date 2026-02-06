package com.testngproject.Test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testngproject.utility.DataProviders;
import com.testngproject.action.Testngactionclass;
import com.testngproject.base.TestngpracticeBase;
import com.testngproject.pages.HomePage;
import com.testngproject.pages.Loginpage;
import com.testngproject.utility.ExtentManager;

public class LoginTest extends TestngpracticeBase {
//	private static ThreadLocal<Loginpage> loginpage=new ThreadLocal<>();
//	private static ThreadLocal<HomePage> homepage=new ThreadLocal<>();
	// Loginpage loginpage;
   //  HomePage homepage;
	
	@DataProvider
	public Object[][] validlogincred(){
		
		return new Object[][] {{"admin","admin123"},{"admin","admin123"}};
		
	}
	
	/*@BeforeMethod
	public synchronized void setuppages() {
		long threadId = Thread.currentThread().getId();
		loginpage = new Loginpage();
		 homepage=new HomePage();
		System.out.println("DEBUG: Thread " + threadId + " created Loginpage object: " + loginpage.hashCode());
		System.out.println("DEBUG: Thread " + threadId + " created Homepage object: " + homepage.hashCode());
	}*/
	
	
	@Test(dataProvider="valid login users",dataProviderClass = DataProviders.class)
	public void validlogin(String username,String password) {
		long threadId = Thread.currentThread().getId();
		//ExtentManager.starttest("valid Login");
		WebDriver currentDriver = getwebdriver();
		System.out.println("Testing Poll SCM");
	    System.out.println("DEBUG: Thread inside valid login" + threadId + " initialized Driver: " + currentDriver.hashCode());
	//	System.out.println("DEBUG: Thread " + threadId + " created Loginpage object: " + loginpage.hashCode());
	//	System.out.println("DEBUG: Thread " + threadId + " created Homepage object: " + homepage.hashCode());
	    getPage().getLoginPage().login(username,password);
	    Assert.assertTrue(getPage().getHomePage().admintabvisble(), "Admin tab is not visibble...");
		ExtentManager.logsucceswithscreenshot(getwebdriver(),"User logged in successfully","valid login test");
		getPage().getHomePage().logout();
	}
	@Test
	public void invalidlogin() {
		//ExtentManager.starttest("invalidlogin");
		long threadId = Thread.currentThread().getId();
		WebDriver currentDriver = getwebdriver();
		System.out.println("DEBUG: Thread inside invalid login" + threadId + " initialized Driver: " + currentDriver.hashCode());
	//	System.out.println("DEBUG: Thread " + threadId + " created Loginpage object: " + loginpage.hashCode());
	//	System.out.println("DEBUG: Thread " + threadId + " created Homepage object: " + homepage.hashCode());
		getPage().getLoginPage().login("admin","admin");
		
		Assert.assertTrue(getPage().getLoginPage().iserrormessagedisplayed(),"Error message is not visible");
		Assert.assertTrue(getPage().getLoginPage().verifyerrormessage("Invalid credentials"),"Error message not matched");
		
	}

}
