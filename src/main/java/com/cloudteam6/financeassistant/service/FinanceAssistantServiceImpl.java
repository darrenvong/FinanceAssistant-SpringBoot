package com.cloudteam6.financeassistant.service;

import org.springframework.stereotype.Service;

import com.cloudteam6.financeassistant.model.Income;
import com.cloudteam6.financeassistant.model.Outgoings;

@Service
public class FinanceAssistantServiceImpl implements FinanceAssistantService {

	@Override
	public double getMonthlyBudget(Income in, Outgoings out) {
		return getAnnualAvailableBudget(in, out) / MONTHS_IN_A_YEAR;
	}

	@Override
	public double getWeeklyBudget(Income in, Outgoings out) {
		return getAnnualAvailableBudget(in, out) / WEEKS_IN_A_YEAR;
	}

	@Override
	public double getDailyBudget(Income in, Outgoings out) {
		return getWeeklyBudget(in, out) / DAYS_IN_A_WEEK;
	}

	private double getAnnualAvailableBudget(Income in, Outgoings out) {
		return in.getTotalAnnualIncome() - out.getTotalAnnualOutgoings();
	}
	

}
