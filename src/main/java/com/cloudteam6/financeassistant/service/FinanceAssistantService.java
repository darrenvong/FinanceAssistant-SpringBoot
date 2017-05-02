package com.cloudteam6.financeassistant.service;

import com.cloudteam6.financeassistant.model.*;

public interface FinanceAssistantService {
	
	public static final int MONTHS_IN_A_YEAR = 12;
	public static final int WEEKS_IN_A_YEAR = 52;
	public static final int DAYS_IN_A_WEEK = 7;
	
	double getMonthlyBudget(Income income, Outgoings outgoings);
	
	double getWeeklyBudget(Income income, Outgoings outgoings);
	
	double getDailyBudget(Income income, Outgoings outgoings);
}
