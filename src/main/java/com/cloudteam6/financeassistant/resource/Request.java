package com.cloudteam6.financeassistant.resource;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Request {
	
	private Map<String, String> income;
	private Map<String, String> outgoings;
	private String weeksuntilnextloan;
	
	public Request() {
		// Income fields
		income = new LinkedHashMap<>();
		income.put("balance", null);
		income.put("balancePeriod", "-1");
		
		income.put("employment", null);
		income.put("employmentPeriod", "1");
		
		income.put("parents", null);
		income.put("parentsPeriod", "1");
		
		income.put("incomeOthers", null);
		income.put("incomeOthersPeriod", "-1");
		
		
		// Outgoing fields
		outgoings = new LinkedHashMap<>();
		outgoings.put("bills", null);
		outgoings.put("billsPeriod", "4");
		
		outgoings.put("mobile", null);
		outgoings.put("mobilePeriod", "4");
		
		outgoings.put("travel", null);
		outgoings.put("travelPeriod", "4");
		
		outgoings.put("rent", null);
		outgoings.put("rentPeriod", "-1");
		
		outgoings.put("outgoingOthers", null);
		outgoings.put("outgoingOthersPeriod", "-1");
		
		this.weeksuntilnextloan = "1";
	}
	
	public Map<String, String> getIncome() {
		return income;
	}

	public void setIncome(Map<String, String> income) {
		this.income = income;
	}

	public Map<String, String> getOutgoings() {
		return outgoings;
	}

	public void setOutgoings(Map<String, String> outgoings) {
		this.outgoings = outgoings;
	}

	public String getWeeksuntilnextloan() {
		return weeksuntilnextloan;
	}

	public void setWeeksuntilnextloan(String weeksuntilnextloan) {
		this.weeksuntilnextloan = weeksuntilnextloan;
	}
}
