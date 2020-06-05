package com.qa.amazonind.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.amazonind.base.BasePage;
import com.qa.amazonind.pages.LandingPage;
import com.qa.amazonind.util.AppConstants;

public class LandingPageTest {

	private static Logger logger = LogManager.getLogger(LandingPageTest.class);
	
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LandingPage landingPage;
	SoftAssert softassert;
	
	@BeforeMethod
	public void setupLandingPageTest()
	{
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		landingPage = new LandingPage(driver);
		softassert = new SoftAssert();
	}
	
	@Test
	public void verifyTitleTest()
	{
		logger.info("verifyTitleTest Started");
		Assert.assertEquals(landingPage.verifyTitle(), AppConstants.LANDING_PAGE_TITLE);
		logger.info("verifyTitleTest Passed");
	}
	
	@Test
	public void verifyHotLinksTest()
	{
		logger.info("verifyHotLinksTest Started");
		ArrayList<String> actulaHotLink = AppConstants.hotLinksActualList();
		ArrayList<String> hotLinks = landingPage.verifyHotLinks();
		for (int i = 0; i < hotLinks.size(); i++) {
			softassert.assertEquals(hotLinks.get(i), actulaHotLink.get(i));
		}
		softassert.assertAll();
		logger.info("verifyHotLinksTest Passed");
	}
	
	@Test
	public void verifyNavToolLinksDisplayedTest()
	{
		logger.info("verifyNavToolLinksDisplayedTest Started");
		List<WebElement> navToolList = landingPage.verifyNavToolLinksDisplayed();
		for (int i = 0; i < navToolList.size(); i++) {
			Assert.assertTrue(navToolList.get(i).isDisplayed());
		}
		logger.info("verifyNavToolLinksDisplayedTest Passed");
	}
	
	@Test
	public void verifyRegistartionFormVisibliltyTest()
	{
		logger.info("verifyRegistartionFormVisibliltyTest Started");
		List<WebElement> parametersList = landingPage.verifyRegistartionFormVisiblilty();
		ArrayList<String> ActualparametersList = AppConstants.registrationFormParameterList();
		for (int i = 0; i < ActualparametersList.size(); i++) {
			Assert.assertEquals(parametersList.get(i).getText(), ActualparametersList.get(i));
		}
		logger.info("verifyRegistartionFormVisibliltyTest Passed");
	}
	
	@AfterMethod
	public void tearDownLandingPageTest()
	{
		driver.quit();
	}
}
