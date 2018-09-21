package com.sampletradestock;

import java.math.BigDecimal;
import java.util.Map;

public class OrderThread extends Thread 
{
	Map<String,BigDecimal> orderStock;
	public OrderThread(Map<String,BigDecimal> orderStock)
	{
		this.orderStock=orderStock;
	}
	
	
	@Override
	public void run() 
	{
		super.run();
	}

	@Override
	public synchronized void start() 
	{
		super.start();
	}
	
}
