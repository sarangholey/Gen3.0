package com.qa.amazonind.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.amazonind.base.BasePage;
import com.qa.amazonind.util.AppConstants;
import com.qa.amazonind.util.ElementActions;

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
	By hotLinks = By.xpath("//div[@id='nav-xshop']/a");
	
	// Page Methods
	public String verifyTitle() {
		return elementActions.doGetPageTitle(AppConstants.LANDING_PAGE_TITLE);
	}
	
	public ArrayList<String> verifyHotLinks()
	{
		List<WebElement> hotLinksList = elementActions.getElementsList(hotLinks);
		ArrayList<String> hotLinkListText = new ArrayList<String>();
		for (int i = 0; i < hotLinksList.size(); i++) {
			hotLinkListText.add(hotLinksList.get(i).getText());
		}
		
		return hotLinkListText;
	}
}
