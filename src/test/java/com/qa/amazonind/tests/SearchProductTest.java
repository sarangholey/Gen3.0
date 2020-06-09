package com.qa.amazonind.tests;

import static org.testng.Assert.assertEquals;

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

import com.qa.amazonind.base.BasePage;
import com.qa.amazonind.pages.SearchProduct;

public class SearchProductTest {

	private static Logger logger = LogManager.getLogger(SearchProductTest.class);
	
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	SearchProduct searchProduct;
	SoftAssert softassert;
	
	@BeforeMethod
	public void setupSearchProductTest()
	{
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		searchProduct = new SearchProduct(driver);
		softassert = new SoftAssert();
	}
		
	@Test
	public void verifyaddSingleProductsToCartTest()
	{
		logger.info("verifyaddSingleProductsToCartTest Started");
		String prodName = "Motorola Pulse 3 Max Over Ear Wired Headphones with Alexa (Black)";
		boolean productCartStatus = searchProduct.verifyaddSingleProductsToCart(prodName);
		Assert.assertEquals(productCartStatus, true);
		logger.info("verifyaddSingleProductsToCartTest Passed");
	}
	
	@Test
	public void verifyKeywordInSuggestionListTest()
	{
		logger.info("verifyKeywordInSuggestionListTest Started");
		ArrayList<String> productNameList = searchProduct.verifyKeywordInSuggestionList("dell");
		for (int i = 0; i < productNameList.size(); i++) {
			System.out.println("Product suggestion list " + (i+1) + ".  " + productNameList.get(i));
			Assert.assertEquals(productNameList.get(i).contains("dell"), true);
		}
		logger.info("verifyKeywordInSuggestionListTest Passed");
	}
	
	@Test
	public void verifyNavigationsPreviousButtonTest()
	{
		logger.info("verifyNavigationsPreviousButtonTest Started");
		Assert.assertEquals(searchProduct.verifyNavigationsPreviousButton("dell"), "a-disabled");
		logger.info("verifyNavigationsPreviousButtonTest Passed");
	}
	
	@Test
	public void verifyNavigationActiveButtonsTest()
	{
		logger.info("verifyNavigationActiveButtonsTest Started");
		ArrayList<String> activeNavButtonsHref = searchProduct.verifyNavigationActiveButtons("dell");
		for (int i = 0; i < activeNavButtonsHref.size(); i++) {
			Assert.assertTrue(activeNavButtonsHref.get(i).contains("https://www.amazon.in/s"));
		}
		logger.info("verifyNavigationActiveButtonsTest Passed");
	}
	
	@AfterMethod
	public void tearDownSearchProductTest()
	{
		driver.quit();
	}
	
}
