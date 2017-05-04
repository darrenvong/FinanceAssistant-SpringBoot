package com.cloudteam6.financeassistant.service;

import java.util.List;

import com.cloudteam6.financeassistant.model.*;

public interface FinanceAssistantService {
	
	public static final int WEEKS_IN_A_MONTH = 4;
	public static final int MONTHS_IN_A_YEAR = 12;
	public static final int WEEKS_IN_A_YEAR = 52;
	public static final int DAYS_IN_A_WEEK = 7;
	
	double getMonthlyBudget(List<MonetaryItem> incomes, List<MonetaryItem> outgoings,
			int weeksUntilNextLoan);
	
	double getWeeklyBudget(List<MonetaryItem> incomes, List<MonetaryItem> outgoings,
			int weeksUntilNextLoan);
	
	double getDailyBudget(List<MonetaryItem> incomes, List<MonetaryItem> outgoings,
			int weeksUntilNextLoan);
}
