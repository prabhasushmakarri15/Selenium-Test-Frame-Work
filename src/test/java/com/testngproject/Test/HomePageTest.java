package com.testngproject.Test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.testngproject.base.TestngpracticeBase;
import com.testngproject.pages.HomePage;
import com.testngproject.pages.Loginpage;
import com.testngproject.utility.ExtentManager;

public class HomePageTest extends TestngpracticeBase{
	
	//private Loginpage loginpage;
	//private HomePage homepage;
	/*@BeforeMethod
	public synchronized void setUppages() {
		long threadId = Thread.currentThread().getId();
		loginpage=new Loginpage();
		homepage=new HomePage();
		System.out.println("DEBUG: Thread " + threadId + " created Loginpage object: " + loginpage.hashCode());
		System.out.println("DEBUG: Thread " + threadId + " created Homepage object: " + homepage.hashCode());
		
	}
	*/
	@Test
	public void checkOrangelogo() {
		//ExtentManager.starttest("checkorange logo Test");
		long threadId = Thread.currentThread().getId();
		WebDriver currentDriver = getwebdriver();
		System.out.println("DEBUG: Thread inside checkorange login" + threadId + " initialized Driver: " + currentDriver.hashCode());
	//	System.out.println("DEBUG: Thread " + threadId + " created Loginpage object: " + loginpage.hashCode());
	//	System.out.println("DEBUG: Thread " + threadId + " created Homepage object: " + homepage.hashCode());
	
		getPage().getLoginPage().login("admin", "admin123");
		Assert.assertTrue(getPage().getHomePage().ornageHRMdisplayed(), "logo is not displayed");
		
	}
	

}



