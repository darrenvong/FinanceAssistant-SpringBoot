package com.cloudteam6.financeassistant.resource;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Request {
	
	private Map<String, String> income;
	private Map<String, String> outgoings;
	
	public Request() {
		// Income fields
		income = new LinkedHashMap<>();
		income.put("loan", null);
		income.put("grant", null);
		income.put("employment", null);
		income.put("parents", null);
		income.put("incomeOthers", null);
		
		// Outgoing fields
		outgoings = new LinkedHashMap<>();
		outgoings.put("bills", null);
		outgoings.put("mobile", null);
		outgoings.put("travel", null);
		outgoings.put("rent", null);
		outgoings.put("outgoingOthers", null);
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
	
	public double getIncomeDouble(String key) {
		return Double.parseDouble(income.get(key));
	}
	
	public double getOutgoingsDouble(String key) {
		return Double.parseDouble(outgoings.get(key));
	}
}
