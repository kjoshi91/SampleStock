package com.sampletradestock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
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
	    List<String> filtered= new ArrayList<String>();
	    
	    Iterator<String> rawIterator=rawList.iterator();
	    while(rawIterator.hasNext())
	    {
		stockSymbol=rawIterator.next();
		temp=YahooFinance.get(stockSymbol);
		dayHigh=temp.getQuote().getDayHigh();
		dayLow=temp.getQuote().getDayLow();
		dayOpen=temp.getQuote().getOpen();
		if(dayOpen==dayLow)
		{
		    System.out.println("Adding to buy&sell filtered... ["+stockSymbol+"] : [Low:"+dayLow+"], [Open:"+dayOpen+"]");
		    filtered.add(stockSymbol);
		}
		else if(dayOpen==dayHigh)
		{
		    System.out.println("Adding to sell&buy filtered... ["+stockSymbol+"] : [High:"+dayHigh+"], [Open:"+dayOpen+"]");
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
    
    public static Map<String,String> placeOrder(List<String> filteredList)
    {
    	
    	
    	return null;
    }
    
}
