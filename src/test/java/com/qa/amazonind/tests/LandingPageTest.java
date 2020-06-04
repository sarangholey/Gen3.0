package com.qa.amazonind.tests;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.amazonind.util.AppConstants;
import com.qa.amazonind.base.BasePage;
import com.qa.amazonind.pages.LandingPage;
import com.qa.amazonind.tests.LandingPageTest;

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
			softassert.assertEquals(actulaHotLink.get(i), hotLinks.get(i));
		}
		softassert.assertAll();
		logger.info("verifyHotLinksTest Passed");
	}
	
	@AfterMethod
	public void tearDownLandingPageTest()
	{
		driver.quit();
	}
}
