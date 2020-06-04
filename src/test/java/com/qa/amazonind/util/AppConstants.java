package com.qa.amazonind.util;

import java.util.ArrayList;

/**
 * @author Sarang
 *	This class having all the constant values
 *	which needed for validation throughout the project
 *	Ex. Page Titles, Explicit Timeout
 */
public class AppConstants {

	public static int DEFAULT_EXPLICIT_TIME_OUT = 20;

	public static final String LANDING_PAGE_TITLE = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";

	public static ArrayList<String> hotLinksActualList()
	{
		ArrayList<String> hotLinksActualList = new ArrayList<String>();
		hotLinksActualList.add("Mobiles");
		hotLinksActualList.add("Best Sellers");
		hotLinksActualList.add("Computers");
		hotLinksActualList.add("Pantry");
		hotLinksActualList.add("Amazon Pay");
		hotLinksActualList.add("New Releases");
		hotLinksActualList.add("Books");
		hotLinksActualList.add("Customer Service");
		hotLinksActualList.add("Sell");
		hotLinksActualList.add("Gift Ideas");
		hotLinksActualList.add("Baby");
		hotLinksActualList.add(""); //AmazonBasics
		hotLinksActualList.add(""); //Coupons
		
		return hotLinksActualList;
	}
	
}
