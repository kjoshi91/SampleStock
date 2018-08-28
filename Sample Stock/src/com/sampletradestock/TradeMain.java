package com.sampletradestock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class TradeMain 
{
	public static void main(String[] args) 
	{
	    List<String> listOfStocks;
	    List<String> filteredStocks;
	    Map<String,BigDecimal> orderplaced;
	    
	    System.out.println("=================== Welcome to auto stock trader application ===================");
	    System.out.println("The application will start trading now...");
	    listOfStocks=TradeUtil.fetchNiftFifty();
	    if(listOfStocks!=null)
	    {
		System.out.println("List of stocks populated...");
		System.out.println("Filtering list...");
		filteredStocks=TradeUtil.filterList(listOfStocks);
		if(filteredStocks==null)
		{
		    System.out.println("No filtered stocks right now");
		}
		else
		{
		    System.out.println("Got some filtered stocks! Proceeding...");
		    orderplaced=TradeUtil.placeOrder(filteredStocks);
		    System.out.println("Order to be placed for: "+orderplaced.toString());
		}
	    }
	    else
	    {
		System.out.println("List is empty!!!");
		System.exit(0);
	    }
	}
}
