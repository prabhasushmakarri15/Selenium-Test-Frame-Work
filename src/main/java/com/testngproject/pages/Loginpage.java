package com.testngproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.testngproject.action.Testngactionclass;
import com.testngproject.base.TestngpracticeBase;

public class Loginpage {
	
	By usernameinput=By.name("username");
	By passwordinput=By.name("password");
	//By submit=By.xpath("//button[@type='submit']");
	private	By submit=By.cssSelector("button[type='submit']");
	By errormessage=By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']");
	private Testngactionclass actiondriver;
	public Loginpage() {
		
		this.actiondriver=TestngpracticeBase.getactiondriver();
		
	}
	
	public void login(String username,String password) {
		actiondriver.entertext(usernameinput,username);
		actiondriver.entertext(passwordinput, password);
		actiondriver.click(submit);
		
	}
	public boolean iserrormessagedisplayed() {
		return actiondriver.isDisplayed(errormessage);
	}
	public String geterrormessage() {
		return actiondriver.gettext(errormessage);
	}
	public boolean verifyerrormessage(String expectederrormessage) {
		return actiondriver.comparevalues(errormessage, expectederrormessage);
		
	}
	
	
}
