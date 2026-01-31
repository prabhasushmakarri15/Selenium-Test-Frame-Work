package com.testngproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.testngproject.action.Testngactionclass;
import com.testngproject.base.TestngpracticeBase;

public class HomePage {
	
	private Testngactionclass actiondriver;
	private By admintab=By.xpath("//span[text()='Admin']");
	private By useridbutton=By.className("oxd-userdropdown-tab");
	private By logout=By.xpath("//a[text()='Logout']");
	private By ornageHRM=By.cssSelector(".oxd-brand-banner>img");
	public HomePage() {
		this.actiondriver=TestngpracticeBase.getactiondriver();;
		
	}
	public boolean admintabvisble() {
		return actiondriver.isDisplayed(admintab);
	}
	public boolean ornageHRMdisplayed() {
		return actiondriver.isDisplayed(ornageHRM);
	}
//	public String homepagetitle() {
	//	return actiondriver.gettitle();
	//}
	
	public void logout() {
		actiondriver.click(useridbutton);
		actiondriver.click(logout);
	}
	
	
}
