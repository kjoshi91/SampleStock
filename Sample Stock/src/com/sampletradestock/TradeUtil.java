package com.sampletradestock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class TradeUtil 
{
    public static List<String> fetchNiftFifty()
    {
	List<String> listOfNiftyFift=new ArrayList<String>();
	String csvFile="D:\\data.csv";
	String line="";
	try(BufferedReader br=new BufferedReader(new FileReader(csvFile)))
	{
	    while((line=br.readLine())!=null)
	    {
		listOfNiftyFift.add(line);
	    }
	    return listOfNiftyFift;
	}
	catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
	
    }
    
    public static List<String> filterList(List<String> rawList)
    {
	try
	{
	    String stockSymbol;
	    Stock temp;
	    BigDecimal dayHigh;
	    BigDecimal dayLow;
	    BigDecimal dayOpen;
	    BigDecimal changePercent;
	    List<String> filtered= new ArrayList<String>();
	    
	    Iterator<String> rawIterator=rawList.iterator();
	    while(rawIterator.hasNext())
	    {
		stockSymbol=rawIterator.next();
		temp=YahooFinance.get(stockSymbol);
		dayHigh=temp.getQuote().getDayHigh();
		dayLow=temp.getQuote().getDayLow();
		dayOpen=temp.getQuote().getOpen();
		changePercent=temp.getQuote().getChangeInPercent();
		
		if(dayOpen.compareTo(dayLow)==0)
		{
		    System.out.println("Adding to buy&sell filtered... ["+stockSymbol+"] : [Low:"+dayLow+"], [Open:"+dayOpen+"], [Change %: "+changePercent+"]");
		    filtered.add(stockSymbol);
		}
		else if(dayOpen.compareTo(dayHigh)==0)
		{
		    System.out.println("Adding to sell&buy filtered... ["+stockSymbol+"] : [High:"+dayHigh+"], [Open:"+dayOpen+"], [Change %: "+changePercent+"]");
		    filtered.add(stockSymbol);
		}
		else
		    System.out.println("Ignoring : "+stockSymbol);
	    }
	    if(filtered.isEmpty())
		return null;
	    else
		return filtered;
	    
	    /*System.out.println("Symbol: "+stockSymbol);
	    System.out.println("Day High: "+temp.getQuote().getDayHigh());*/
	} catch (Exception e)
	{
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return null;
	}
	
    }
    
    public static Map<String, BigDecimal> placeOrder(List<String> filteredList)
    {
    	if(filteredList!=null)
    	{
    		String stockSymbol;
			Stock temp;
			BigDecimal tempChangePercent=new BigDecimal(0);
			String selectedStock="";
			Iterator<String> filteredIterator=filteredList.iterator();
			Map<String,BigDecimal> tempMap=new Hashtable<>();
    		try {
				
				while(filteredIterator.hasNext())
				{
					stockSymbol=filteredIterator.next();
					temp=YahooFinance.get(stockSymbol);
					if((tempChangePercent.compareTo(temp.getQuote().getChangeInPercent()))<0)
					{
						tempChangePercent=temp.getQuote().getChangeInPercent();
						selectedStock=stockSymbol;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		tempMap.put(selectedStock, tempChangePercent);
    		return tempMap;
    	}
    	else
    	{
    		return null;
    	}
    }
    
}
