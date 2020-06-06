package com.qa.amazonind.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.amazonind.util.ElementActions;

public class SearchProduct {

	WebDriver driver;
	Properties prop;
	ElementActions elementActions;
	
	// constructor
	public SearchProduct(WebDriver driver) {
		this.driver = driver;
		elementActions = new ElementActions(this.driver);
	}
	
	// Page Objects
	By searchBox = By.id("twotabsearchtextbox");
	By searchButton = By.xpath("//div[@class='nav-search-submit nav-sprite']//input[@type='submit']");
	By productsList = By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']//div[@class='a-section a-spacing-none']//a/span");
	By addToCartButton = By.id("add-to-cart-button");
	By addedToCartMessage = By.xpath("//div[@id='huc-v2-confirm-text-container']//h1");
	By cartIconButton = By.xpath("//a[@id='nav-cart']");
	By productInCart = By.xpath("//div[@class='a-fixed-left-grid-col a-col-right']//ul//a[@class='a-link-normal sc-product-link']/span");
	
	By productSuggestions = By.xpath("//div[@id='suggestions-template']//div[@class='s-suggestion']");
	
	
	// Page Methods
	public boolean verifyaddSingleProductsToCart(String productName)
	{
		elementActions.doSendKeys(searchBox, productName);
		elementActions.doClick(searchButton);
		elementActions.waitForElementClickable(productsList);
		List<WebElement> listOfProducts = elementActions.getElementsList(productsList);
		for (int i = 0; i <listOfProducts.size(); i++) {
			if(listOfProducts.get(i).getText().contains(productName))
			{
				elementActions.doClickOnWebElement(listOfProducts.get(i));
			}
		}
		elementActions.switchToWindowId(driver, 1);
		elementActions.waitForElementClickable(addToCartButton);
		elementActions.doClick(addToCartButton);
		elementActions.waitForElementVisible(addedToCartMessage);
		driver.close();
		elementActions.switchToWindowId(driver, 0);
		driver.navigate().refresh();
		elementActions.doClick(cartIconButton);
		List<WebElement> productsInCartList = elementActions.getElementsList(productInCart);
		for (int i = 0; i < productsInCartList.size(); i++) {
			if(productsInCartList.get(i).getText().contains(productName))
			{
				return true;
			}
		}
		
		return false;
		
	}
	
	public ArrayList<String> verifyKeywordInSuggestionList(String productName)
	{
		elementActions.doSendKeys(searchBox, productName);
		elementActions.waitForElementVisible(productSuggestions);
		List<WebElement> productList = elementActions.getElementsList(productSuggestions);
		ArrayList<String> namesOfProdList = new ArrayList<String>();
		for (int i = 0; i < productList.size(); i++) {
			namesOfProdList.add(productList.get(i).getAttribute("data-keyword"));
		}
		return namesOfProdList;
	}
	
}
