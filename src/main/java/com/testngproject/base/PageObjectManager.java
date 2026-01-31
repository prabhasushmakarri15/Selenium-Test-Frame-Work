package com.testngproject.base;

import org.openqa.selenium.WebDriver;

import com.testngproject.pages.HomePage;
import com.testngproject.pages.Loginpage;

public class PageObjectManager {
	private WebDriver driver;
    private Loginpage loginpage;
    private HomePage homepage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }
    public Loginpage getLoginPage() {
        if (loginpage == null) {
            // First time calling the method: create the object
            loginpage = new Loginpage();
        }
        // Every time after: just return the existing object
        return loginpage;
    }
    public HomePage getHomePage() {
        if (homepage == null) {
            // First time calling the method: create the object
        	homepage = new HomePage();
        }
        // Every time after: just return the existing object
        return homepage;
    }
    

}
