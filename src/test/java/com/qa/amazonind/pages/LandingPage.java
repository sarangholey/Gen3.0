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
	By navToolLinks = By.xpath("//div[@id='nav-tools']/a");
	
	By accountsAndListsLink = By.xpath("//div[@id='nav-tools']//span[contains(text(),'Account & Lists')][1]");
	By startHereLink = By.xpath("//div[@id='nav-flyout-accountList']//a[contains(text(),'Start here.')]");
	By createAccountText = By.xpath("//form[@id='ap_register_form']//h1");
	By createAccountParametersList = By.xpath("//form[@id='ap_register_form']//label");
	
	By hamburgerMenuIcon = By.xpath("//div[@id='nav-belt']//a[@id='nav-hamburger-menu']/i");
	By HMenuMainSections = By.xpath("//div[@id='hmenu-canvas']//ul[@class='hmenu hmenu-visible']/li/div");
	
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
	
	public List<WebElement> verifyNavToolLinksDisplayed()
	{
		return elementActions.getElementsList(navToolLinks);
	}
	
	public List<WebElement> verifyRegistartionFormVisiblilty()
	{
		elementActions.doMoveToElement(accountsAndListsLink);
		elementActions.waitForElementClickable(startHereLink);
		elementActions.doActionsClick(startHereLink);
		elementActions.waitForElementVisible(createAccountParametersList);
		List<WebElement> listofParamaters = elementActions.getElementsList(createAccountParametersList);
		return listofParamaters;
		
	}
	
	public ArrayList<String> verifyHamburgerMenuMainCategory()
	{
		elementActions.waitForElementClickable(hamburgerMenuIcon);
		elementActions.doClick(hamburgerMenuIcon);
		elementActions.waitForElementVisible(HMenuMainSections);
		List<WebElement> mainSectionList = elementActions.getElementsList(HMenuMainSections);
		ArrayList<String> HmMenuSections = new ArrayList<String>();
		for (int i = 0; i < mainSectionList.size(); i++) {
			HmMenuSections.add(mainSectionList.get(i).getText());
			
		}
		return HmMenuSections;
		
	}
}
