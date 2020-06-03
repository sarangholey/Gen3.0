package com.qa.amazonind.pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.qa.amazonind.util.ElementActions;
import com.qa.amazonind.util.AppConstants;
import com.qa.amazonind.base.BasePage;

public class LandingPage extends BasePage {
	
	WebDriver driver;
	Properties prop;
	ElementActions elementActions;
	
	// constructor
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		elementActions = new ElementActions(this.driver);
	}
	
	// Page Objects
	
	// Page Methods
	public String verifyTitle() {
		return elementActions.doGetPageTitle(AppConstants.LANDING_PAGE_TITLE);
	}
}
